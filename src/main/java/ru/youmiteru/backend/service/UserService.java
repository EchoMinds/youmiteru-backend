package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.convertors.UsersConvertors;
import ru.youmiteru.backend.domain.Role;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.dto.SeasonDto.FavoriteSeason;
import ru.youmiteru.backend.dto.SeasonDto.RatedSeason;
import ru.youmiteru.backend.dto.UserDTO;
import ru.youmiteru.backend.dto.responses.ResponseForUserProfile;
import ru.youmiteru.backend.exceptions.UserNotFoundException;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.UserRepository;
import ru.youmiteru.backend.security.AuthorizationSuccessHandlerImpl;
import ru.youmiteru.backend.security.jwt.JwtService;


import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UsersConvertors usersConvertors;
    private final SeasonConvertors seasonConvertors;
    private final SeasonRepository seasonRepository;

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return usersConvertors.convertUserToUserDto(
            user, getUserFavoriteSeasonList(id), getUserRatedSeasonList(id));
    }

    public List<FavoriteSeason> getUserFavoriteSeasonList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getFavoriteSeasonList().stream().map(seasonConvertors::convertSeasonToFavoriteSeasonDTO).toList();
    }

    public List<RatedSeason> getUserRatedSeasonList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getRatingList().stream().map(m -> seasonConvertors.convertSeasonToRatedSeason(m.getSeason(),
            m.getValue())).toList();
    }

    //USER PANEL
    public HttpStatus addFavorite(Long user_id, Long season_id) {
        Optional<User> user = userRepository.findById(user_id);
        Optional<Season> season = seasonRepository.findById(season_id);
        System.out.println(user);


        if (user.isPresent() && season.isPresent()) {
            Season newSeason = season.get();
            User newUser = user.get();

            List<Season> listSeason = new ArrayList<>(newUser.getFavoriteSeasonList());
            List<User> listUser = new ArrayList<>(newSeason.getThisUserLikeThisAnime());
            listSeason.add(newSeason);
            listUser.add(newUser);
            newUser.setFavoriteSeasonList(listSeason);
            newSeason.setThisUserLikeThisAnime(listUser);

            userRepository.save(newUser);
            seasonRepository.save(newSeason);

            return HttpStatus.OK;
        }

        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus deleteFavorite(Long user_id, Long season_id) {
        Optional<User> user = userRepository.findById(user_id);
        Optional<Season> season = seasonRepository.findById(season_id);

        System.out.println(user);
        if (user.isPresent() && season.isPresent()) {
            User trueUser = user.get();
            Season trueSeason = season.get();

            trueUser.getFavoriteSeasonList().remove(trueSeason);
            trueSeason.getThisUserLikeThisAnime().remove(trueUser);

            userRepository.save(trueUser);
            seasonRepository.save(trueSeason);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    //USER OAUTH REG + AUTH
    public ResponseEntity<?> getTokenForSecurity(OAuth2User principal,
                                                 OAuth2AuthenticationToken authentication,
                                                 JwtService jwtService) {
        String token = null;
        String userLogin = null;
        String userEmail = null;
        String userProfilePicture = null;
        User getUser = null;
        if (authentication == null || principal == null) {
            return ResponseEntity.ok("YOU ARE BANNED!");
        } else {
            String provider = authentication.getAuthorizedClientRegistrationId();

            //get provider and get in principal name, email and etc
            if ("google".equals(provider)) {
                userLogin = principal.getAttribute("name");
                userEmail = principal.getAttribute("email");
                userProfilePicture = principal.getAttribute("picture");
                System.out.println("User ID: " + principal.getAttribute("sub"));

            } else if ("yandex".equals(provider)) {
                userLogin = principal.getAttribute("login");
                userEmail = principal.getAttribute("default_email");
                userProfilePicture = principal.getAttribute("default_avatar_id");
                System.out.println("User ID: " + principal.getAttribute("id"));

            }

            // Проверяем, существует ли пользователь в базе данных
            if (!userRepository.existsByEmail(userEmail)) {
                // Создаем и сохраняем нового пользователя
                getUser = new User();

                getUser.setName(userLogin);
                getUser.setEmail(userEmail);
                getUser.setProfileImageUrl(userProfilePicture);
                getUser.setRole(Role.USER);
                getUser.setCreationTime(LocalDateTime.now());
                userRepository.save(getUser);
                token = String.format(jwtService.generateToken(
                    new AuthorizationSuccessHandlerImpl.UserInfoFromToken(
                        principal.getAttribute("sub"),
                        userLogin,
                        userEmail,
                        "userAvatar",
                        getUser.getRole().name()
                    )
                ));
            } else {
                getUser = userRepository.findByEmail(principal.getAttribute("email")) != null ?
                    userRepository.findByEmail(principal.getAttribute("email")) :
                    userRepository.findByEmail(principal.getAttribute("default_email"));
                token = String.format(jwtService.generateToken(
                    new AuthorizationSuccessHandlerImpl.UserInfoFromToken(
                        principal.getAttribute("sub"),
                        userLogin,
                        userEmail,
                        "userAvatar",
                        getUser.getRole().name()
                    )
                ));
                // Возвращаем ответ
            }
        }

        return new ResponseEntity<>(new ResponseForUserProfile(token,getUser), HttpStatus.OK);
    }
}
