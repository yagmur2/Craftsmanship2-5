package parser;

import java.util.Objects;

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

    
    //build function for connectors
    public static final Connector build(TerminalSymbol connectorType){
    	
    	Objects.requireNonNull(connectorType, "connector type is null or input is null when build connector");
    	
    	if(LegalConnectorType(connectorType)) {
    		return cache.get(connectorType,Connector::new);
    		}
    	throw new IllegalArgumentException("Illegal connector type to build");
    }

    
    //helper function detect if the connector type is legal or not
    private static boolean LegalConnectorType(TerminalSymbol type){
    	
    	for (TerminalSymbol symbol : TerminalSymbol.values()) {
    		if(type == symbol)
    			return true;
    	}
    	return false;
    }


    //over ride to string function
    @Override
    public String toString(){
    		return Objects.requireNonNull(TerminalSymbol.TerminalStringTable.get(this.getType()), "The Conector String not found in the table");
        }
}
