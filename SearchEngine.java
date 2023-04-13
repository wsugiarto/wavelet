
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    
    List<String> Strs = new ArrayList<>();


    public String handleRequest(URI url) {

        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                Strs.add(parameters[1]);
                return String.format("%s has been added to the list", parameters[1]);
            }
        }
        if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                
                String toReturn = "";
                for (String word: Strs){
                    if (word.contains(parameters[1])){
                        toReturn += word + " ";
                    }
                }
                

                return String.format(toReturn);
            }
        }
        return "404 Not Found!";
        
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
