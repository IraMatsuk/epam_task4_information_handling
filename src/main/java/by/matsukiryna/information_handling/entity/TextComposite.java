package by.matsukiryna.information_handling.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TextComposite implements TextComponent {
    private List<TextComponent> textComponents = new ArrayList<>();
    private ComponentType componentType;

    public TextComposite() {
    }

    public TextComposite(ComponentType componentType) {
        this.componentType = componentType;
    }


    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public ComponentType getType() {
        return componentType;
    }

    @Override
    public List<TextComponent> getChild() {
        return List.copyOf(textComponents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return (textComponents != null ? textComponents == that.textComponents : that.textComponents == null)
                && (componentType != null ? componentType == that.componentType : that.componentType == null);
    }

    @Override
    public int hashCode() {
        int result = textComponents != null ? textComponents.hashCode() : 0;
        result = 31 * result + (componentType != null ? componentType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        String delimiter = componentType.getDelimiter();
        for (TextComponent textComponent : textComponents) {
            sb.append(textComponent.toString()).append(delimiter);
        }
        return sb.toString();
    }

    public static class SentenceAmountComparator implements Comparator<TextComponent> {

        @Override
        public int compare(TextComponent o1, TextComponent o2) {
            return Integer.compare(o1.getChild().size(), o2.getChild().size());
        }
    }
}
