package com.springboot.train.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/img")
public class ImgController {
	@RequestMapping("/index")
	public String imgindex() {
		return "img/index";
	}

	@ResponseBody
	@RequestMapping("/uploadImg")
	public String uploadImg(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
	    System.out.println("开始");  
        String path = TrainController.class.getClassLoader().getResource("").getPath()+"static/upload";  
        String fileName = UUID.randomUUID().toString().replace("-", "")+"."+file.getOriginalFilename().split("\\.")[1];  
        System.out.println(path);  
        File targetFile = new File(path);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        targetFile=new File(path,fileName);
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return  "{\"name\":\""+ targetFile.getName() +"\", \"originalName\": \""+ file.getOriginalFilename() +"\", \"size\": "+ file.getSize()+", \"state\": \"SUCCESS\", \"type\": \""+ file.getOriginalFilename().split("\\.")[1] +"\", \"url\": \"" +path.split("classes")[1]+"/"+fileName +"\"}";
	}

	@RequestMapping("/createServlet")
	public void processRequest(@RequestParam(value = "p")String Pic, @RequestParam(value = "x") int PointX, @RequestParam(value = "y") int PointY,
			@RequestParam(value = "w") int CutWidth, @RequestParam(value = "h") int CutHeight,
			@RequestParam(value = "pw") int PicWidth, @RequestParam(value = "ph") int PicHeight,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String path = TrainController.class.getClassLoader().getResource("").getPath()+Pic;

		ServletOutputStream responseOutputStream = response.getOutputStream();

		cut(path, responseOutputStream, CutWidth, CutHeight, PointX, PointY, PicWidth, PicHeight);

		// 以下关闭输入流！
		responseOutputStream.flush();

		responseOutputStream.close();
	}

	/**
	 * 图片切割
	 * 
	 * @param srcImageFile
	 *            图片地址
	 * @param responseOutputStream
	 *            servlet输出流
	 * @param w
	 *            切割宽度
	 * @param h
	 *            切割高度
	 * @param x1
	 *            开始x结点（left）
	 * @param y1
	 *            开始y结点（top）
	 * @param sw
	 *            图片宽度
	 * @param sh
	 *            图片高度
	 */
	public void cut(String srcImageFile, ServletOutputStream responseOutputStream, int w, int h, int x1, int y1, int sw,
			int sh) {
		try {
			Image img;

			ImageFilter cropFilter;
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));

			if (sw >= w && sh >= h) {

				Image image = bi.getScaledInstance(sw, sh, Image.SCALE_DEFAULT);

				// 剪切起始坐标点
				int x = x1;
				int y = y1;

				int destWidth = w; // 切片宽度
				int destHeight = h; // 切片高度

				// 图片比例
				double pw = sw;
				double ph = sh;

				double m = (double) sw / pw;
				double n = (double) sh / ph;

				int wth = (int) (destWidth * m);
				int hth = (int) (destHeight * n);
				int xx = (int) (x * m);
				int yy = (int) (y * n);

				// 四个参数分别为图像起点坐标和宽高
				// 即: CropImageFilter(int x,int y,int width,int height)

				cropFilter = new CropImageFilter(xx, yy, wth, hth);
				img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));

				BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null); // 绘制缩小后的图
				g.dispose();
				// 输出为文件
				ImageIO.write(tag, "JPEG", responseOutputStream);
				ImageIO.write(tag, "JPEG", new File("D:/1.jpg"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
