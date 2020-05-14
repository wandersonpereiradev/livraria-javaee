package br.com.livraria.javaee.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import javax.servlet.http.Part;

	public class FileSaver {

		public static final String SERVER_PATH = "c:";

		//tornando o método genérico
		public String caminhoCapaLivro(Part arquivo, String path) {
			String relativePath = path + "/" + arquivo.getSubmittedFileName();
			try {
				arquivo.write(SERVER_PATH + "/" + relativePath);
				return relativePath;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public static void transfer(Path source, OutputStream outputStream) {
			
			try {
				FileInputStream inputStream = new FileInputStream(source.toFile());
				//inputChanel 
				try(ReadableByteChannel inputChanel = Channels.newChannel(inputStream);
						WritableByteChannel outputChanel =  Channels.newChannel(outputStream)){
					ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
					
					while(inputChanel.read(buffer) != -1) {
						//zerando os bites do buffer para que ele possa ler os dados de saída
						buffer.flip();
						outputChanel.write(buffer);
						//limpando o buffer para que o 'while' possa ler mais informação
						buffer.clear();
					}
					
				} catch (IOException e) {
					throw new RuntimeException(e);
				};
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}		
			
		}

}
