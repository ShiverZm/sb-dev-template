package team.shiver.handler;




import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team.shiver.exception.BizException;
import team.shiver.model.JsonResult;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public String bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("发生业务异常！原因是：",e);
        return e.toString();
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value =NullPointerException.class)
    public String exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return e.toString();
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value =Exception.class)
    public String exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return e.toString();
    }

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadResult";
    }
}
