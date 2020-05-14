package br.com.livraria.javaee.servlets;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livraria.javaee.infra.FileSaver;

@WebServlet("/file/*")
public class FileServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI().split("/file")[1];
		Path source = Paths.get(FileSaver.SERVER_PATH + "/" + path);
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor("file:" + source);
		
		// limpando o response para garantir que venha vazio
		resp.reset();
		
		resp.setContentType(contentType);
		resp.setHeader("Content-Length", String.valueOf(Files.size(source)));
		resp.setHeader("Content-Disposition", "filename=\"" + source.getFileName().toString() + "\"");
		
		//fazendo a transferência do arquivo
		FileSaver.transfer(source, resp.getOutputStream());
	}

}
