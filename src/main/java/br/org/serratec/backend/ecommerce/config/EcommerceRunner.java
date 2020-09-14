package br.org.serratec.backend.ecommerce.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import br.org.serratec.backend.ecommerce.model.Foto;
import br.org.serratec.backend.ecommerce.repository.FotoRepository;

@Configuration
public class EcommerceRunner implements ApplicationRunner{

	@Autowired 
	FotoRepository fotoRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (fotoRepository.findById(1).get().getData().length==0) {
			List<Foto> lista = fotoRepository.findAll();
			for(Foto foto : lista) {
				String nome = "/fotos/" + foto.getNome();
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(nome);
				byte[] data = convertStreamToByteArray(inputStream, 1024);
				System.out.println(data.length);
				foto.setData(data);
				fotoRepository.save(foto);
			}
		};
	}
	
	public byte[] convertStreamToByteArray(InputStream stream, long size) throws IOException {
	    if (size > Integer.MAX_VALUE) {
	        return new byte[0];
	    }

	    byte[] buffer = new byte[(int)size];
	    ByteArrayOutputStream os = new ByteArrayOutputStream();

	    int line = 0;
	    while ((line = stream.read(buffer)) != -1) {
	        os.write(buffer, 0, line);
	    }
	    stream.close();
	    os.flush();
	    os.close();
	    return os.toByteArray();
	}

}
