package team.shiver.controller;

import com.google.zxing.WriterException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import team.shiver.utils.QRCodeGenerator;

import java.io.IOException;

@Controller
public class Index {


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
}
