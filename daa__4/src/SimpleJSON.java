import java.util.*;
import java.util.regex.*;

public class SimpleJSON {
    public static Map<String, Object> parse(String text) {
        return (Map<String, Object>) parseValue(new Tokenizer(text));
    }

    private static Object parseValue(Tokenizer t) {
        String tok = t.next();
        if (tok.equals("{")) {
            Map<String, Object> map = new LinkedHashMap<>();
            while (!t.peek().equals("}")) {
                String key = t.nextString();
                t.expect(":");
                Object val = parseValue(t);
                map.put(key, val);
                if (t.peek().equals(",")) t.next();
            }
            t.expect("}");
            return map;
        } else if (tok.equals("[")) {
            List<Object> list = new ArrayList<>();
            while (!t.peek().equals("]")) {
                list.add(parseValue(t));
                if (t.peek().equals(",")) t.next();
            }
            t.expect("]");
            return list;
        } else if (tok.startsWith("\"")) {
            return tok.substring(1, tok.length() - 1);
        } else {
            return tok.contains(".") ? Double.parseDouble(tok) : Integer.parseInt(tok);
        }
    }

    private static class Tokenizer {
        private final List<String> tokens;
        private int pos = 0;

        Tokenizer(String text) {
            tokens = new ArrayList<>();
            Matcher m = Pattern.compile("\\{|\\}|\\[|\\]|:|,|\"[^\"]*\"|[\\w.\\-]+").matcher(text);
            while (m.find()) tokens.add(m.group());
        }

        String peek() {
            return pos < tokens.size() ? tokens.get(pos) : "";
        }

        String next() {
            return tokens.get(pos++);
        }

        void expect(String s) {
            if (!next().equals(s)) throw new RuntimeException("Expected " + s);
        }

        String nextString() {
            String t = next();
            return t.substring(1, t.length() - 1);
        }
    }
}