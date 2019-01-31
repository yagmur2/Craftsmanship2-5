package Tokens;
import Tokens.*;


public final class Connector extends AbstractToken{
    private static  Cache<TerminalSymbol, Connector> cache = new Cache<TerminalSymbol, Connector>();
    TerminalSymbol type ;


    private Connector(TerminalSymbol type){
        this.type = type;
    }
    @Override
    public TerminalSymbol getType() {
        return this.type;
    }

    public static final Connector build(TerminalSymbol connectorType){
    if(LegalConnectorTyoe(connectorType)){
        return cache.get(connectorType,Connector::new);
    }else if(connectorType == null){
        throw new NullPointerException("Null input connector type received");
    }else if( ! LegalConnectorTyoe(connectorType) ){
        throw new IllegalArgumentException("Illegal connector type to build");
    }else{
        throw new SecurityException("Somehow you make to this Weird Case");
    }
    }

    private static boolean LegalConnectorTyoe(TerminalSymbol type){
        return type == TerminalSymbol.CLOSE || type == TerminalSymbol.OPEN || type == TerminalSymbol.DIVIDE || type == TerminalSymbol.TIMES || type == TerminalSymbol.MINUS || type == TerminalSymbol.PLUS;
    }

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
                    throw new IllegalStateException("This Type of connector shoudlnet be called");
            }

        }
}
