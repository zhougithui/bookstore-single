package org.bear.bookstore.web.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Item;
 
public class RssViewer extends AbstractRssFeedView {
 
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
        HttpServletRequest request) {
 
        feed.setTitle("zh rss");
        feed.setDescription("Java Tutorials and Examples");
        feed.setLink("http://www.zh.com");
 
        super.buildFeedMetadata(model, feed, request);
    }
 
 
    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
 
        @SuppressWarnings("unchecked")
        List<SampleContent> listContent = (List<SampleContent>) model.get("feedContent");
        List<Item> items = new ArrayList<Item>(listContent.size());
 
        for(SampleContent tempContent : listContent ){
 
            Item item = new Item();
 
            Content content = new Content();
            content.setValue(tempContent.getSummary());
            item.setContent(content);
 
            item.setTitle(tempContent.getTitle());
            item.setLink(tempContent.getUrl());
            item.setPubDate(tempContent.getCreatedDate());
 
            items.add(item);
        }
 
        return items;
    }
 
} 