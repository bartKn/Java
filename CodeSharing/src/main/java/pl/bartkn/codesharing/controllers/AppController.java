package pl.bartkn.codesharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.bartkn.codesharing.code.Code;
import pl.bartkn.codesharing.db.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AppController {

    private final CodeService codeService;

    @Autowired
    public AppController(CodeService codeService) {
        this.codeService = codeService;
    }

    @RequestMapping(value = "/code/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getHtml(@PathVariable String id, HttpServletResponse response) {
        response.addHeader("Content-type", "text/html");
        ModelAndView model = new ModelAndView();
        try {
            Code codeToReturn = codeService.findCodeById(UUID.fromString(id));
            if (codeToReturn == null) {
                response.setStatus(404);
                model.setViewName("notFound");
            } else {
                model.addObject("codeBody", codeToReturn.getCode());
                model.addObject("date", codeToReturn.getFormattedDate());
                model.addObject("time_left", codeToReturn.getTime());
                model.addObject("views_left", codeToReturn.getViews());
                model.addObject("time_flag", codeToReturn.isTimeFlag());
                model.addObject("views_flag", codeToReturn.isViewsFlag());
                model.setViewName("code");
            }
            return model;
        } catch (IllegalArgumentException ex) {
            response.setStatus(404);
            model.setViewName("notFound");
            return model;
        }
    }

    @RequestMapping(value = "/code/new", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getNewCode(HttpServletResponse response) {
        response.addHeader("Content-type", "text/html");
        ModelAndView model = new ModelAndView();
        model.setViewName("getCode");
        return model;
    }

    @RequestMapping(value = "/code/latest", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getLatestSnippets(HttpServletResponse response) {
        response.addHeader("Content-type", "text/html");
        ModelAndView model = new ModelAndView();
        model.addObject("snippetList", codeService.getCodesWithoutRestrictions());
        System.out.println(codeService.getCodesWithoutRestrictions());
        model.setViewName("getLatest");
        return model;
    }
}