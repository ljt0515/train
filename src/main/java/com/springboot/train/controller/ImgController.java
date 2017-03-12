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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
	public String uploadImg(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		System.out.println("开始");
		String path = TrainController.class.getClassLoader().getResource("").getPath() + "static/temp";
		String fileName = UUID.randomUUID().toString().replace("-", "") + "."
				+ file.getOriginalFilename().split("\\.")[1];
		System.out.println(path);
		File targetFile = new File(path);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		targetFile = new File(path, fileName);
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"name\":\"" + targetFile.getName() + "\", \"originalName\": \"" + file.getOriginalFilename()
				+ "\", \"size\": " + file.getSize() + ", \"state\": \"SUCCESS\", \"type\": \""
				+ file.getOriginalFilename().split("\\.")[1] + "\", \"url\": \"" + path.split("classes")[1] + "/"
				+ fileName + "\"}";
	}
	@ResponseBody
	@RequestMapping("/createServlet")
	public Map<String, String> processRequest(@RequestParam(value = "p") String Pic, @RequestParam(value = "x") int x,
			@RequestParam(value = "y") int y, @RequestParam(value = "w") int destWidth,
			@RequestParam(value = "h") int destHeight, @RequestParam(value = "pw") int pw,
			@RequestParam(value = "ph") int ph)
			throws ServletException, IOException {
		Map<String,String> map=new HashMap<String,String>();
		map.put("code", "error");
		map.put("message", "保存失败");
		String path = TrainController.class.getClassLoader().getResource("").getPath() + Pic;
		Image img;
		ImageFilter cropFilter;
		// 读取源图像
		BufferedImage bi = ImageIO.read(new File(path));
		
		if (pw >= destWidth && ph >= destHeight) {
			Image image = bi.getScaledInstance(pw, ph, Image.SCALE_DEFAULT);
			// 剪切起始坐标点
			// 图片比例
			double m = (double) pw / pw;
			double n = (double) ph / ph;
			int wth = (int) (destWidth * m);
			int hth = (int) (destHeight * n);
			int xx = (int) (x * m);
			int yy = (int) (y * n);
			// 四个参数分别为图像起点坐标和宽高
			cropFilter = new CropImageFilter(xx, yy, wth, hth);
			img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
			BufferedImage tag = new BufferedImage(destWidth,destHeight, BufferedImage.TYPE_3BYTE_BGR);
			Graphics g = tag.getGraphics();
			g.drawImage(img, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			// 输出为文件
			String pathname = TrainController.class.getClassLoader().getResource("").getPath() + "static/upload";
			File targetFile = new File(pathname);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			ImageIO.write(tag, "JPEG",new File(pathname+"/"+Pic.split("temp")[1]));
			map.put("code", "success");
			map.put("message", "保存成功");
		}
		return map;
	}
}
