package by.matsukiryna.information_handling.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent textComponent);

    void remove(TextComponent textComponent);

    ComponentType getType();

    List<TextComponent> getChild();

    String toString();
}
