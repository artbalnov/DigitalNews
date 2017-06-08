package artbalnov.digitalnewsrefactored.base.di.api;

import artbalnov.digitalnewsrefactored.base.di.base.BaseComponent;

public interface HasComponent<Component extends BaseComponent> {
    Component provideComponent();

    Component getComponent();
}
