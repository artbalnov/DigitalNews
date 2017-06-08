package artbalnov.digitalnewsrefactored.base.di.base;


import artbalnov.digitalnewsrefactored.base.di.api.HasComponent;

public interface BasePresentationComponent<ComponentProvider extends HasComponent> extends BaseComponent {
    void inject(ComponentProvider destination);
}
