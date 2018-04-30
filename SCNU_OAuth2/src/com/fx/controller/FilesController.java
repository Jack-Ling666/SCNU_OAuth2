package com.fx.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FilesController {

	@RequestMapping(value = "/download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String zipBasePath = request.getSession().getServletContext().getRealPath("/logs");
		System.out.println("starting download...");

		/* 判断文件夹是否有文件，遍历文件夹中的文件。。。 */
		String path = zipBasePath;
		Vector<String> vecFile = new Vector<String>();
		recursion(path, vecFile);
		System.out.println("文件路径分别是：");
		for (String fileName : vecFile) {
			System.out.println(fileName);
		}

		/* 设置下载文件的名称 */
		String fileName = "logs.zip";
		response.setContentType("text/html; charset=UTF-8"); // 设置编码字符
		response.setContentType("application/x-msdownload"); // 设置内容类型为下载类型
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);// 设置下载的文件名称
		OutputStream out = response.getOutputStream(); // 创建页返回方式为输出流，会自动弹出下载框

		/* 创建压缩文件需要的空的zip包 ，这里是自动生成的，不用我们自己去生成 */
		String zipFilePath = zipBasePath + "temp.zip";
		System.out.println("create the empty zip file successfully...");

		/* 根据临时的zip压缩包路径，创建zip文件 */
		File zip = new File(zipFilePath);
		if (!zip.exists()) {
			zip.createNewFile();
		}
		System.out.println("create the  zip file successfully...");

		/* 创建zip文件输出流 */
		FileOutputStream fos = new FileOutputStream(zip);
		ZipOutputStream zos = new ZipOutputStream(fos);
		System.out.println("create the empty zip stream successfully....");

		/* 循环读取文件路径集合，获取每一个文件的路径（将文件一个一个进行压缩） */
		for (String fp : vecFile) {
			File f = new File(fp); // 根据文件路径创建文件
			zipFile(f, zos); // 将每一个文件写入zip文件包内，即进行打包
		}
		zos.close();
		System.out.println("files zipped over, starting to download");

		/* 将打包后的文件写到客户端，有两种方法可以实现（下面会进行介绍），这里使用缓冲流输出 */
		InputStream fis = new BufferedInputStream(new FileInputStream(zipFilePath));
		byte[] buff = new byte[4096];
		int size = 0;
		while ((size = fis.read(buff)) != -1) {
			out.write(buff, 0, size);
		}
		System.out.println("package is download successfully");

		// 释放和关闭输入输出流
		out.flush();
		out.close();
		fis.close();
	}

	public void zipFile(File inputFile, ZipOutputStream zipoutputStream) {
		try {
			if (inputFile.exists()) { // 判断文件是否存在
				if (inputFile.isFile()) { // 判断是否属于文件，还是文件夹

					// 创建输入流读取文件
					FileInputStream fis = new FileInputStream(inputFile);
					BufferedInputStream bis = new BufferedInputStream(fis);

					// 将文件写入zip内，即将文件进行打包
					ZipEntry ze = new ZipEntry(inputFile.getName()); // 获取文件名
					zipoutputStream.putNextEntry(ze);

					// 写入文件的方法，同上
					byte[] b = new byte[1024];
					long l = 0;
					while (l < inputFile.length()) {
						int j = bis.read(b, 0, 1024);
						l += j;
						zipoutputStream.write(b, 0, j);
					}
					// 关闭输入输出流
					bis.close();
					fis.close();
				} else { // 如果是文件夹，则使用穷举的方法获取文件，写入zip
					try {
						File[] files = inputFile.listFiles();
						for (int i = 0; i < files.length; i++) {
							zipFile(files[i], zipoutputStream);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recursion(String root, Vector<String> vecFile) {
		String path = root;
		File file = new File(root);
		if (file.exists()) {
			System.out.println("this file is exit!");

			File[] subFile = file.listFiles();
			for (int i = 0; i < subFile.length; i++) {
				if (subFile[i].isDirectory()) {
					recursion(subFile[i].getAbsolutePath(), vecFile);
				} else {
					String filename = subFile[i].getName();
					vecFile.add(path + File.separator + filename);
				}
			}
		} else {
			System.out.println("this file is not exit!");
		}

	}

}