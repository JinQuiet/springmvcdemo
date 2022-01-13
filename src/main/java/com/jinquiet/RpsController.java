package com.jinquiet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RpsController {

    private RpsGameService rpsGameService;

    @Autowired    
    public RpsController(RpsGameService rpsGameService) {
        this.rpsGameService = rpsGameService;
    }

    @GetMapping("/hello")
    public String helloAction() {
        return "hello";
    }

    @GetMapping("/rps")
    public ModelAndView rpsActionBotDriven() {

        ModelAndView mav = new ModelAndView("rps");

            RpsGameModel gameResult = rpsGameService.play(Rps.ROCK.name());

            mav.addObject("rpsResult", gameResult.getGameResult());
            mav.addObject("playerAction", gameResult.getPlayerAction());
            mav.addObject("opponentAction", gameResult.getOpponentAction());

        return mav;
    }

    @RequestMapping(value = "/rps/{action}", method = RequestMethod.GET)    
    public ModelAndView rpsActionPlayerDriven(@PathVariable("action") String action) {

        ModelAndView mav = new ModelAndView("rps");

            RpsGameModel gameResult = rpsGameService.play(action);

            mav.addObject("rpsResult", gameResult.getGameResult());
            mav.addObject("playerAction", gameResult.getPlayerAction());
            mav.addObject("opponentAction", gameResult.getOpponentAction());

        return mav;
    }


}
