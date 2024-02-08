package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Comment;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.CommentDTO;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.dto.TitleDTO;
import ru.youmiteru.backend.exceptions.SeasonNotFoundException;
import ru.youmiteru.backend.repositories.CommentRepository;
import ru.youmiteru.backend.repositories.RatingRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;

    //Return data for HomePage
    public SeasonDTO.Response.ListHomePage getAllSeasonForHomePage() {


        SeasonDTO.Response.ListHomePage listHomePage = new SeasonDTO.Response.ListHomePage();

        List<SeasonDTO.Response.HomePage> anons = seasonRepository.findAnnouncement()
            .stream().map(this::convertToSeasonDtoForHomePage).collect(Collectors.toList());
        List<SeasonDTO.Response.HomePage> release = seasonRepository.findRecent()
            .stream().map(this::convertToSeasonDtoForHomePage).collect(Collectors.toList());
        List<SeasonDTO.Response.HomePage> banner = seasonRepository.findBanner()
            .stream().map(this::convertToSeasonDtoForHomePage).collect(Collectors.toList());
        List<SeasonDTO.Response.HomePage> popular = seasonRepository.findPopular()
            .stream().map(this::convertToSeasonDtoForHomePage).collect(Collectors.toList());

        listHomePage.setBanners(banner);
        listHomePage.setAnnounced_seasons(anons);
        listHomePage.setPopular_seasons(popular);
        listHomePage.setRecent_released_seasons(release);

        return listHomePage;
    }

    //Convert Seasons to DTO FOR HOME PAGE!!!!
    private SeasonDTO.Response.HomePage convertToSeasonDtoForHomePage(Season season) {
        SeasonDTO.Response.HomePage seasonDTO = new SeasonDTO.Response.HomePage();

        seasonDTO.setSeasonId(season.getId());
        seasonDTO.setSeasonName(season.getName());
        seasonDTO.setDescription(season.getDescription());
        seasonDTO.setImageUrl(season.getSeasonImageUrl());

        return seasonDTO;
    }

    //return Data for Season page
    public SeasonDTO.Response.SeasonPage getSeasonPage(Long id) {
        Season seasonPage = seasonRepository.findById(id).orElseThrow(SeasonNotFoundException::new);
        return convertToDtoForSeasonPage(seasonPage);
    }

    public SeasonDTO.Response.SeasonPage convertToDtoForSeasonPage(Season seasonPage) {
        SeasonDTO.Response.SeasonPage dto = new SeasonDTO.Response.SeasonPage();

        dto.setSeasonId(seasonPage.getId());
        dto.setImageUrl(seasonPage.getSeasonImageUrl());
        dto.setSeasonName(seasonPage.getName());
        dto.setAnimeFormat(String.valueOf(seasonPage.getAnimeFormat()));
        dto.setDescription(seasonPage.getDescription());
        dto.setReleaseDate(seasonPage.getReleaseDate());
        dto.setTitleState(seasonPage.getTitleState());
        dto.setAgeRestriction(seasonPage.getAgeRestriction());
        dto.setYearSeason(seasonPage.getYearSeason());
        dto.setTitleInformationForSeasonPages(convertToTitleInformation(seasonPage.getTitle()));
        dto.setCommentsList(getCommentsList(seasonPage));
        dto.setRating(getRating(seasonPage.getId()));

        return dto;
    }

    //get comments
    private List<CommentDTO.Response.Comments> getCommentsList(Season seasonPage) {
        return seasonPage.getSeasonCommentList().stream()
            .filter(comment -> comment.getReplyTo() == null)
            .map(this::convertToCommentDto).toList();
    }

    //return TitleDto for season Page
    private TitleDTO.Response.TitleInformationForSeasonPage convertToTitleInformation(Title title) {
        return new TitleDTO.Response.TitleInformationForSeasonPage(title.getId(),
            title.getName(), title.getTitleImageUrl());

    }

    //return commentDTO for season Page
    private CommentDTO.Response.Comments convertToCommentDto(Comment comment) {
        CommentDTO.Response.Comments commentDTO = new CommentDTO.Response.Comments();

        commentDTO.setCommentId(comment.getId());
        commentDTO.setCreationDate(comment.getCreationDate());
        commentDTO.setRating(comment.getRatingValue());
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setWriterId(comment.getWriter().getId());
        commentDTO.setProfileImageUrl(comment.getWriter().getProfileImageUrl());
        commentDTO.setSubcommentsList(getSubCommentsList(comment));

        return commentDTO;
    }

    //get SubComments
    private List<CommentDTO.Response.Comments> getSubCommentsList(Comment comment) {
        return commentRepository.findByReplyTo(comment).stream()
            .map(this::convertToCommentDto).toList();
    }

    private Double getRating(Long id) {

        //get class rating for season
        List<Rating> valueListWithRaitingClass = ratingRepository.findAllBySeason_Id(id);
        //get only value
        List<Integer> ratings = valueListWithRaitingClass.stream().map(Rating::getValue).toList();

        double rating = ratings.stream().mapToDouble(d -> d).average().orElse(0.0);
        double scaleRounding = 100;
        //rounding
        double result = Math.ceil(rating * scaleRounding) / scaleRounding;

        return result;
    }


    //safe moment
//    private CommentDTO.Response.SubComments convertToSubCommentDto(Comment comment) {
//        CommentDTO.Response.SubComments subComment = new CommentDTO.Response.SubComments(
//            comment.getId(), comment.getCreationDate(), comment.getMessage(), comment.getWriter().getProfileImageUrl(),
//            comment.getReplyTo().getId(), comment.getWriter().getId(), comment.getRatingValue()
//        );
//
//        return subComment;
//    }
}
