package kr.codesquad.todo9.todo.api;

import kr.codesquad.todo9.todo.domain.card.Card;
import kr.codesquad.todo9.todo.domain.log.LogDTO;
import kr.codesquad.todo9.todo.domain.user.User;
import kr.codesquad.todo9.todo.requestobject.ContentsObject;
import kr.codesquad.todo9.todo.requestobject.MoveCardObject;
import kr.codesquad.todo9.todo.service.card.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CardAPIController {

    private static final Logger log = LoggerFactory.getLogger(CardAPIController.class);
    private static final Long DEFAULT_BOARD_ID = 1L;

    private final CardService cardService;

    public CardAPIController(CardService cardService) {this.cardService = cardService;}

    @GetMapping("/board/{boardId}/column/{boardKey}/card/list")
    public List<Card> showCardListOfColumnOfBoard(@PathVariable Long boardId, @PathVariable int boardKey) {
        return cardService.getCardList(boardId, boardKey);
    }

    @GetMapping("/column/{boardKey}/card/list")
    public List<Card> showCardListOfColumn(@PathVariable int boardKey) {
        return showCardListOfColumnOfBoard(DEFAULT_BOARD_ID, boardKey);
    }

    @PostMapping("/board/{boardId}/column/{boardKey}/card")
    public LogDTO addCard(HttpServletRequest request,
                          @PathVariable Long boardId,
                          @PathVariable int boardKey,
                          @RequestBody @Valid ContentsObject contentsObject) {
        User user = (User) request.getAttribute("user");
        log.debug("firstUser: {}", user);
        return cardService.addCard(boardId, boardKey, user, contentsObject);
    }

    @PostMapping("/column/{boardKey}/card")
    public LogDTO addCard(HttpServletRequest request,
                          @PathVariable int boardKey,
                          @RequestBody @Valid ContentsObject contentsObject) {
        return addCard(request, DEFAULT_BOARD_ID, boardKey, contentsObject);
    }

    @PutMapping("/board/{boardId}/column/{boardKey}/card/{columnKey}")
    public LogDTO editCard(HttpServletRequest request,
                           @PathVariable Long boardId,
                           @PathVariable int boardKey,
                           @PathVariable int columnKey,
                           @RequestBody @Valid ContentsObject contentsObject) {
        User user = (User) request.getAttribute("user");
        log.debug("firstUser: {}", user);
        return cardService.editCard(boardId, boardKey, columnKey, user, contentsObject);
    }

    @PutMapping("/column/{boardKey}/card/{columnKey}")
    public LogDTO editCard(HttpServletRequest request,
                           @PathVariable int boardKey,
                           @PathVariable int columnKey,
                           @RequestBody @Valid ContentsObject contentsObject) {
        return this.editCard(request, DEFAULT_BOARD_ID, boardKey, columnKey, contentsObject);
    }

    @DeleteMapping("/board/{boardId}/column/{boardKey}/card/{columnKey}")
    public LogDTO deleteCard(HttpServletRequest request,
                             @PathVariable Long boardId,
                             @PathVariable int boardKey,
                             @PathVariable int columnKey) {
        User user = (User) request.getAttribute("user");
        log.debug("firstUser: {}", user);
        return cardService.deleteCard(boardId, boardKey, columnKey, user);
    }

    @DeleteMapping("/column/{boardKey}/card/{columnKey}")
    public LogDTO deleteCard(HttpServletRequest request, @PathVariable int boardKey, @PathVariable int columnKey) {
        return deleteCard(request, DEFAULT_BOARD_ID, boardKey, columnKey);
    }

    @PatchMapping("/board/{boardId}/column/{boardKey}/card/{columnKey}")
    public LogDTO moveCard(HttpServletRequest request,
                           @PathVariable Long boardId,
                           @PathVariable int boardKey,
                           @PathVariable int columnKey,
                           @RequestBody MoveCardObject moveCardObject) {
        User user = (User) request.getAttribute("user");
        log.debug("firstUser: {}", user);
        return cardService.moveCard(boardId, boardKey, columnKey, user, moveCardObject);
    }

    @PatchMapping("/column/{boardKey}/card/{columnKey}")
    public LogDTO moveCard(HttpServletRequest request,
                           @PathVariable int boardKey,
                           @PathVariable int columnKey,
                           @RequestBody MoveCardObject moveCardObject) {
        return moveCard(request, DEFAULT_BOARD_ID, boardKey, columnKey, moveCardObject);
    }
}
