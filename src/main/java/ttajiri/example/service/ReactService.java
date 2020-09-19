package ttajiri.example.service;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Service;

@Service
public class ReactService {

    private final Context polyglot;

    public ReactService() {
        polyglot = Context.create();
    }

    public Value render() {
        return polyglot.eval("js", "let test = 'Hello GraalVM!';  test;");
    }

}
