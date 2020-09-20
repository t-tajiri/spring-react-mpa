package ttajiri.example.service;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReactService {
    private static final String RENDER_FILE_NAME = "render.js";
    private final Context polyglot;

    public ReactService() {
        polyglot = Context.create("js");
    }

    public Value render() throws IOException {
        var defineServerFunction = getSource("static/server.js");
        var defineAppFunction    = getSource("static/component/App.js");
        var defineRenderFunction = getSource("templates/" + RENDER_FILE_NAME);
        polyglot.eval(defineAppFunction);
        polyglot.eval(defineServerFunction);
        polyglot.eval(defineRenderFunction);
        return polyglot.getBindings("js").getMember("render").execute();
    }

    private Source getSource(String path) throws IOException {
        var file = new ClassPathResource(path).getFile();
        var language = Source.findLanguage(file);
        return Source.newBuilder(language, file).build();
    }

}
