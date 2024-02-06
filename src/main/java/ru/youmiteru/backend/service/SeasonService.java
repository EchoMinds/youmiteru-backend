package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Comment;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.CommentDTO;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.dto.TitleDTO;
import ru.youmiteru.backend.exceptions.SeasonNotFoundException;
import ru.youmiteru.backend.repositories.CommentRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private final CommentRepository commentRepository;

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

        List<CommentDTO.Response.Comments> commentsList = seasonPage.getSeasonCommentList().stream()
            .filter(comment -> comment.getReplyTo() == null)
            .map(this::convertToCommentDto).toList();

        dto.setCommentsList(commentsList);

        return dto;
    }

    private TitleDTO.Response.TitleInformationForSeasonPage convertToTitleInformation(Title title) {
        return new TitleDTO.Response.TitleInformationForSeasonPage(title.getId(), title.getName(), title.getTitleImageUrl());

    }

    private CommentDTO.Response.Comments convertToCommentDto(Comment comment) {
//        return new CommentDTO.Response.Comments(comment.getId(), comment.getCreationDate(), comment.getMessage()
//            , comment.getWriter().getProfileImageUrl(), null);
        CommentDTO.Response.Comments commentDTO = new CommentDTO.Response.Comments();

        commentDTO.setCommentId(comment.getId());
        commentDTO.setCreationDate(comment.getCreationDate());
        commentDTO.setRating(comment.getRatingValue());
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setWriterId(comment.getWriter().getId());
        commentDTO.setProfileImageUrl(comment.getWriter().getProfileImageUrl());


        List<CommentDTO.Response.Comments> subCommentsList = commentRepository.findByReplyTo(comment).stream()
            .map(this::convertToCommentDto).toList();

        commentDTO.setSubcommentsList(subCommentsList);

        return commentDTO;
    }

//    private CommentDTO.Response.SubComments convertToSubCommentDto(Comment comment) {
//        CommentDTO.Response.SubComments subComment = new CommentDTO.Response.SubComments(
//            comment.getId(), comment.getCreationDate(), comment.getMessage(), comment.getWriter().getProfileImageUrl(),
//            comment.getReplyTo().getId(), comment.getWriter().getId(), comment.getRatingValue()
//        );
//
//        return subComment;
//    }
}
