package by.matsukiryna.information_handling.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SymbolLeaf implements TextComponent {
    private static Logger logger = LogManager.getLogger();
    private ComponentType componentType;
    private char symbol;

    public SymbolLeaf() {
    }

    public SymbolLeaf(ComponentType componentType, char symbol) {
        this.componentType = componentType;
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent textComponent) {
        logger.error("The operation \"add\" is unsupported for symbol");
        throw new UnsupportedOperationException("The operation \"add\" is unsupported for symbol");
    }

    @Override
    public void remove(TextComponent textComponent) {
        logger.error("The operation \"remove\" is unsupported for symbol");
        throw new UnsupportedOperationException("The operation \"remove\" is unsupported for symbol");
    }

    @Override
    public ComponentType getType() {
        return componentType;
    }

    @Override
    public List<TextComponent> getChild() {
        logger.error("The operation for getting children on symbol is unsupported");
        throw new UnsupportedOperationException("The operation for getting children on symbol is unsupported");
    }
}
