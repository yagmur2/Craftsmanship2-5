package Tokens;
import Tokens.*;


public final class Connector extends AbstractToken{
    //VARIABLES
    private static  Cache<TerminalSymbol, Connector> cache = new Cache<TerminalSymbol, Connector>();
    TerminalSymbol type ;


    //private constructor
    private Connector(TerminalSymbol type){
        this.type = type;
    }


    //FUNCTIONS
    @Override
    public TerminalSymbol getType() {
        return this.type;
    }

    //bulid function for connectors
    public static final Connector build(TerminalSymbol connectorType){
    if( LegalConnectorType(connectorType) ){
        return cache.get(connectorType,Connector::new);
    }else if( connectorType == null ){
        throw new NullPointerException("Null input connector type received");
    }else if( ! LegalConnectorType(connectorType) ){
        throw new IllegalArgumentException("Illegal connector type to build");
    }else{
        throw new SecurityException("Connector type is not null, not legal and not illegal, weird case");
    }
    }




    //helper function detect if the connector type is legal or not
    private static boolean LegalConnectorType(TerminalSymbol type){
        return type == TerminalSymbol.CLOSE || type == TerminalSymbol.OPEN || type == TerminalSymbol.DIVIDE || type == TerminalSymbol.TIMES || type == TerminalSymbol.MINUS || type == TerminalSymbol.PLUS;
    }


    //over ride to string function
        @Override
    public String toString(){
            switch (this.type){
                case OPEN:
                    return "(";
                case CLOSE:
                    return ")";
                case PLUS:
                    return "+";
                case MINUS:
                    return "-";
                case TIMES:
                    return "*";
                case DIVIDE:
                    return "/";
                default:
                    throw new IllegalStateException("This Type of connector shouldn't be called");
            }

        }
}
