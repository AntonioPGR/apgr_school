package school.pachecos.infra.uri;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class URIService {

	@Value("${api.routes.base_path}")
	String base_path;

	public URI createReturnURI(String unique_path) {
		try{
			return new URI(base_path + unique_path);
		} catch (URISyntaxException exception){
			return null;
		}
	}

}
