package team.shiver.controller;

import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team.shiver.utils.QRCodeGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Api(description = "主页跳转")
@Controller
public class Index {
    @ApiOperation(value = "hello" ,  notes="欢迎页面")
    @RequestMapping("/hello")
    public String  hello(ModelMap modelMap ){
        modelMap.addAttribute("name", "这是我");
        modelMap.addAttribute("age", "24");

        return "hello";
    }

    @ApiOperation(value = "ex" ,  notes="异常")
    @RequestMapping("/ex")
    public void  ex(ModelMap modelMap ) throws Exception{

        throw new Exception("异常");
    }

    @ApiOperation(value = "生成二维码" ,  notes="生成二维码")
    @RequestMapping("/qrcode")
    public String qrcode() {
        return "qrcode";
    }

    @RequestMapping(value="/qrimage")
    public ResponseEntity<byte[]> getQRImage() {


        byte[] qrcode = null;
        try {
            //二维码内的信息
            String info = new String("你好吗"   );

            byte[] utf8Bytes = info.getBytes("UTF-8");

            String str = new String(utf8Bytes, "ISO-8859-1");
            qrcode = QRCodeGenerator.getQRCodeImage(str, 360, 360);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }

        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]> (qrcode, headers, HttpStatus.CREATED);
    }


    @RequestMapping("/file")
    public String  file(ModelMap modelMap ){
        return "upload";
    }

    @ApiOperation(value = "upload" ,  notes="上传文件")
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception{
        //String UPLOADED_FOLDER = ResourceUtils.getURL("static").getPath();
        String UPLOADED_FOLDER = "C:\\Users\\Administrator\\Desktop\\tmp";

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/uploadResult";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadResult";
    }

    @GetMapping("/uploadResult")
    public String uploadStatus() {
        return "uploadResult";
    }

}
