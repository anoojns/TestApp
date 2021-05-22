package com.interview.test.model.api_response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.interview.test.model.Poster;

import java.util.List;

public class PosterListResponse {


    /**
     * page : {"title":"Romantic Comedy","total-content-items":"54","page-num":"1","page-size":"20","content-items":{"content":[{"name":"The Birds","poster-image":"poster1.jpg"},{"name":"Rear Window","poster-image":"poster2.jpg"}]}}
     */

    private PageResponse page;

    public PageResponse getPage() {
        return page;
    }

    public void setPage(PageResponse page) {
        this.page = page;
    }

    public static class PageResponse {
        /**
         * title : Romantic Comedy
         * total-content-items : 54
         * page-num : 1
         * page-size : 20
         * content-items : {"content":[{"name":"The Birds","poster-image":"poster1.jpg"},{"name":"Rear Window","poster-image":"poster2.jpg"}]}
         */

        private String title;

        @SerializedName("total-content-items")
        private int totalcontentitems;

        @SerializedName("page-num")
        private int pagenum;

        @SerializedName("page-size")
        private int pagesize;

        @SerializedName("content-items")
        private Contentitems contentitems;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTotalcontentitems() {
            return totalcontentitems;
        }

        public void setTotalcontentitems(int totalcontentitems) {
            this.totalcontentitems = totalcontentitems;
        }

        public int getPagenum() {
            return pagenum;
        }

        public void setPagenum(int pagenum) {
            this.pagenum = pagenum;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public Contentitems getContentitems() {
            return contentitems;
        }

        public void setContentitems(Contentitems contentitems) {
            this.contentitems = contentitems;
        }

        public static class Contentitems {
            private List<Poster> content;

            public List<Poster> getContent() {
                return content;
            }

            public void setContent(List<Poster> content) {
                this.content = content;
            }

        }
    }


    public static PosterListResponse getClassDataFromJsonString(String jsonData) {
        PosterListResponse response = new PosterListResponse();

        try {
            Gson gson = new Gson();
            response= gson.fromJson(jsonData,PosterListResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
