package parser;
// import tokens if needed
import java.util.List;

final class ParseState {
    //Variables

    private final boolean success;
    private final Node node ;
    private final List<Token> remainder  ;
    final static ParseState FAILURE = new ParseState(false,null,null);


    private ParseState(boolean success, Node node, List<Token> tokenlist){
        this.success = success;
        this.node = node;
        this.remainder = tokenlist;
    }
    public boolean isSuccess(){
        return this.success;
    }

    public Node getNode(){
        return this.node;
    }

    public List<Token> getRemainder(){
        return this.remainder;
    }


    public boolean hasNoRemainder(){
        return remainder.isEmpty();
    }
 

    public static final ParseState build(Node node, List<Token> givenRemainder){
        if((node!=null) && (givenRemainder != null)){
            //do procedure here
            return new ParseState(true, node, givenRemainder);
        }
        else
            throw  new NullPointerException("Either remainder or node input is NULL");
    }

}

