package me.ppting.zhihudaily;

import java.util.List;

/**
 * Gson解析类
 * api是 http://news-at.zhihu.com/api/1.2/news/before/20151106
 * Created by PPTing on 15/11/3.
 */
public class ZhihuInfo {

    /**
     * date : 20151106
     * news : [{"title":"假如一种药每次吃三片，为什么不把三片直接制成一片？","url":"http://news-at.zhihu.com/api/1.2/news/7401921","image":"http://pic3.zhimg.com/e04d601bcbfee699271fb07f00d3e38e.jpg","share_url":"http://daily.zhihu.com/story/7401921","thumbnail":"http://pic1.zhimg.com/3d1502476048098564330af87b49d1f8.jpg","ga_prefix":"110611","id":7401921},{"title":"跑步后膝盖痛主要有 3 种原因，可以自我治疗和预防","url":"http://news-at.zhihu.com/api/1.2/news/7399914","image":"http://pic2.zhimg.com/659570359b527817136177e98c92d601.jpg","share_url":"http://daily.zhihu.com/story/7399914","thumbnail":"http://pic2.zhimg.com/3f1a832d1cbae3a41e5cca564ecbd14d.jpg","ga_prefix":"110610","id":7399914},{"title":"一点儿也不忙，居然还觉得烦","url":"http://news-at.zhihu.com/api/1.2/news/7401935","image":"http://pic1.zhimg.com/1666291dfa088a59a45e6c9735bde484.jpg","share_url":"http://daily.zhihu.com/story/7401935","thumbnail":"http://pic1.zhimg.com/5ed56b9d2a3f8099c2b50d66a59ad530.jpg","ga_prefix":"110609","id":7401935},{"title":"年轻人如何预防和及时发现癌症？","url":"http://news-at.zhihu.com/api/1.2/news/7399592","image":"http://pic3.zhimg.com/2c7e6726d5aaf965012e3ecc1ceafa12.jpg","share_url":"http://daily.zhihu.com/story/7399592","thumbnail":"http://pic2.zhimg.com/b46dad75528567f070bac64ea12db049.jpg","ga_prefix":"110608","id":7399592},{"title":"华尔街出了新玩法，这个系统拒绝高频交易员","url":"http://news-at.zhihu.com/api/1.2/news/7397840","image":"http://pic1.zhimg.com/5a7c22b372603f7b82032255ea1b2fd0.jpg","share_url":"http://daily.zhihu.com/story/7397840","thumbnail":"http://pic3.zhimg.com/82cdc51217e16305fecc98e2826b30de.jpg","ga_prefix":"110607","id":7397840},{"title":"贫穷对人的这三个影响，已经被证实了","url":"http://news-at.zhihu.com/api/1.2/news/7402588","image":"http://pic1.zhimg.com/15eba4fc5f190b3c3ac6071ba7b87158.jpg","share_url":"http://daily.zhihu.com/story/7402588","thumbnail":"http://pic1.zhimg.com/979d6668ceef7fc44f66f506c948ef04.jpg","ga_prefix":"110607","id":7402588},{"title":"一种高风险的隐私可能被忽视了，就是你的基因信息","url":"http://news-at.zhihu.com/api/1.2/news/7390842","image":"http://pic2.zhimg.com/2be9885fc5a6d362516695767919b435.jpg","share_url":"http://daily.zhihu.com/story/7390842","thumbnail":"http://pic4.zhimg.com/69845375772d6ef0364e415db501077b.jpg","ga_prefix":"110607","id":7390842},{"title":"瞎扯 · 如何正确地吐槽","url":"http://news-at.zhihu.com/api/1.2/news/7402148","image":"http://pic3.zhimg.com/4d04adefe14598850913ee6fc4aa72ae.jpg","share_url":"http://daily.zhihu.com/story/7402148","thumbnail":"http://pic2.zhimg.com/228be506c1b80b77adc64ed0b72a46e9.jpg","ga_prefix":"110606","id":7402148}]
     * display_date : 2015.11.6 星期五
     */

    private String date;
    private String display_date;
    /**
     * title : 假如一种药每次吃三片，为什么不把三片直接制成一片？
     * url : http://news-at.zhihu.com/api/1.2/news/7401921
     * image : http://pic3.zhimg.com/e04d601bcbfee699271fb07f00d3e38e.jpg
     * share_url : http://daily.zhihu.com/story/7401921
     * thumbnail : http://pic1.zhimg.com/3d1502476048098564330af87b49d1f8.jpg
     * ga_prefix : 110611
     * id : 7401921
     */

    private List<NewsEntity> news;

    public void setDate(String date) {
        this.date = date;
    }

    public void setDisplay_date(String display_date) {
        this.display_date = display_date;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }

    public String getDate() {
        return date;
    }

    public String getDisplay_date() {
        return display_date;
    }

    public List<NewsEntity> getNews() {
        return news;
    }

    public static class NewsEntity {
        private String title;
        private String url;
        private String image;
        private String share_url;
        private String thumbnail;
        private String ga_prefix;
        private int id;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public String getImage() {
            return image;
        }

        public String getShare_url() {
            return share_url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public int getId() {
            return id;
        }
    }
}
