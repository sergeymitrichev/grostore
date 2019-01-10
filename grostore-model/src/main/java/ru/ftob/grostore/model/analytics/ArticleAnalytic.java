package ru.ftob.grostore.model.analytics;

import ru.ftob.grostore.model.base.AbstractBaseEntity;

public class ArticleAnalytic extends AbstractBaseEntity implements Viewable {

    private Long views;
    
    @Override
    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
