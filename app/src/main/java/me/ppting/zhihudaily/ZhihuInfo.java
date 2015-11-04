package me.ppting.zhihudaily;

import java.util.ArrayList;
import java.util.List;

/**
 * Gson解析类
 * api是 http://news-at.zhihu.com/api/1.2/news/latest
 * Created by PPTing on 15/11/3.
 */
public class ZhihuInfo {

    /**
     * date : 20151103
     * news : [{"title":"如果「原子弹上的飞船」能实现，人类可能更早踏上太空文明之路","url":"http://news-at.zhihu.com/api/1.2/news/7389557","image":"http://pic3.zhimg.com/d62720c12925aa6a3819b7b639a4435e.jpg","share_url":"http://daily.zhihu.com/story/7389557","thumbnail":"http://pic1.zhimg.com/704278533d20b1723e0dfc1d069688a0.jpg","ga_prefix":"110314","id":7389557},{"title":"略凶残，这些昆虫都可以吃","url":"http://news-at.zhihu.com/api/1.2/news/7385613","image":"http://pic4.zhimg.com/c6693152fa94448eaccf15fd90fd910f.jpg","share_url":"http://daily.zhihu.com/story/7385613","thumbnail":"http://pic4.zhimg.com/6b78b3c9d64c82ad731205f19a73f3a7.jpg","ga_prefix":"110313","id":7385613},{"title":"「我花了四千多给自己 DIY 了个机床」，这才是工程师的浪漫啊！","url":"http://news-at.zhihu.com/api/1.2/news/7386118","image":"http://pic3.zhimg.com/e633b7ffc053c32ad28bdf8faf6b1432.jpg","share_url":"http://daily.zhihu.com/story/7386118","thumbnail":"http://pic1.zhimg.com/8503ca2ed49009d86735c1ea3b27bdf0.jpg","ga_prefix":"110312","id":7386118},{"title":"时间过得快，我想要抓住时间","url":"http://news-at.zhihu.com/api/1.2/news/7373565","image":"http://pic3.zhimg.com/2df3f087d4dadb58c4a4946b91257746.jpg","share_url":"http://daily.zhihu.com/story/7373565","thumbnail":"http://pic4.zhimg.com/2c4a7f564006efa7f143ce4fb326d7a7.jpg","ga_prefix":"110311","id":7373565},{"title":"一双安全舒适的跑鞋，不仅结构好，还要适合你的脚型","url":"http://news-at.zhihu.com/api/1.2/news/7374994","image":"http://pic4.zhimg.com/ffac634a5be854b64279df42c549d76b.jpg","share_url":"http://daily.zhihu.com/story/7374994","thumbnail":"http://pic1.zhimg.com/c07d2004f8a95d4426a64db14559ef2c.jpg","ga_prefix":"110310","id":7374994},{"title":"这个药被用来让很多人说实话，效果嘛，见仁见智","url":"http://news-at.zhihu.com/api/1.2/news/7386571","image":"http://pic4.zhimg.com/4d6b1ba46f5037d6266d93d82843c403.jpg","share_url":"http://daily.zhihu.com/story/7386571","thumbnail":"http://pic3.zhimg.com/a6adacd4303acab82109529c09346a26.jpg","ga_prefix":"110309","id":7386571},{"title":"关于正当防卫，可能存在一些误区","url":"http://news-at.zhihu.com/api/1.2/news/7386292","image":"http://pic4.zhimg.com/8a3a831ff5873027953323a2088cfb77.jpg","share_url":"http://daily.zhihu.com/story/7386292","thumbnail":"http://pic4.zhimg.com/4667388c9ee828aac33c9aa40134272b.jpg","ga_prefix":"110308","id":7386292},{"title":"解开中国房价之谜，大量二三线城市房价上涨与购买力增长相当","url":"http://news-at.zhihu.com/api/1.2/news/7382881","image":"http://pic2.zhimg.com/ba1ec0437cc8d5367a516ff69b01ea89.jpg","share_url":"http://daily.zhihu.com/story/7382881","thumbnail":"http://pic4.zhimg.com/24cf299f505b77bddabdc79480f73427.jpg","ga_prefix":"110307","id":7382881},{"title":"往黑洞里伸一根棍子，一半在里面一半在外面，棍子还好吗？","url":"http://news-at.zhihu.com/api/1.2/news/7388060","image":"http://pic3.zhimg.com/675ec45100a5afcc3be9bab0a8c7e6ee.jpg","share_url":"http://daily.zhihu.com/story/7388060","thumbnail":"http://pic1.zhimg.com/447c36a847e39d0be32aa788a65e33f8.jpg","ga_prefix":"110307","id":7388060},{"title":"电梯里常常有一面镜子，用处特别大","url":"http://news-at.zhihu.com/api/1.2/news/7385482","image":"http://pic1.zhimg.com/f459d5efd6087f773f73b73eb47ab29c.jpg","share_url":"http://daily.zhihu.com/story/7385482","thumbnail":"http://pic2.zhimg.com/edc69d0594066586b8fe80da45cc5409.jpg","ga_prefix":"110307","id":7385482},{"title":"瞎扯 · 如何正确地吐槽","url":"http://news-at.zhihu.com/api/1.2/news/7384064","image":"http://pic3.zhimg.com/6bbfb8754858f30ade1a99b6a5173326.jpg","share_url":"http://daily.zhihu.com/story/7384064","thumbnail":"http://pic3.zhimg.com/a7826c1d58cb0eefbcca8729523379aa.jpg","ga_prefix":"110306","id":7384064}]
     * is_today : true
     * top_stories : [{"image_source":"Taki Steve / CC BY","title":"这个药被用来让很多人说实话，效果嘛，见仁见智","url":"http://news-at.zhihu.com/api/1.2/news/7386571","image":"http://pic4.zhimg.com/4d6b1ba46f5037d6266d93d82843c403.jpg","share_url":"http://daily.zhihu.com/story/7386571","ga_prefix":"110309","id":7386571},{"image_source":"DaiLuo / CC-BY","title":"解开中国房价之谜，大量二三线城市房价上涨与购买力增长相当","url":"http://news-at.zhihu.com/api/1.2/news/7382881","image":"http://pic2.zhimg.com/ba1ec0437cc8d5367a516ff69b01ea89.jpg","share_url":"http://daily.zhihu.com/story/7382881","ga_prefix":"110307","id":7382881},{"image_source":"包一磊 / 知乎","title":"「我花了四千多给自己 DIY 了个机床」，这才是工程师的浪漫啊！","url":"http://news-at.zhihu.com/api/1.2/news/7386118","image":"http://pic3.zhimg.com/e633b7ffc053c32ad28bdf8faf6b1432.jpg","share_url":"http://daily.zhihu.com/story/7386118","ga_prefix":"110312","id":7386118},{"image_source":"Death and Life","title":"这些画金光闪闪玉体横陈，看起来那么赤裸、铺张、不怀好意","url":"http://news-at.zhihu.com/api/1.2/news/7385202","image":"http://pic4.zhimg.com/6e37012ca36409606253564171c63267.jpg","share_url":"http://daily.zhihu.com/story/7385202","ga_prefix":"110219","id":7385202},{"image_source":"《纪念碑谷》","title":"制作纪念碑谷的是一家什么样的公司？","url":"http://news-at.zhihu.com/api/1.2/news/7369083","image":"http://pic4.zhimg.com/6a48981bde1c29d3262a0069e7f80547.jpg","share_url":"http://daily.zhihu.com/story/7369083","ga_prefix":"110212","id":7369083}]
     * display_date : 2015.11.3 星期二
     */

    private String date;
    private boolean is_today;
    private String display_date;
    /**
     * title : 如果「原子弹上的飞船」能实现，人类可能更早踏上太空文明之路
     * url : http://news-at.zhihu.com/api/1.2/news/7389557
     * image : http://pic3.zhimg.com/d62720c12925aa6a3819b7b639a4435e.jpg
     * share_url : http://daily.zhihu.com/story/7389557
     * thumbnail : http://pic1.zhimg.com/704278533d20b1723e0dfc1d069688a0.jpg
     * ga_prefix : 110314
     * id : 7389557
     */

    private List<NewsEntity> news = new ArrayList<NewsEntity>();;
    /**
     * image_source : Taki Steve / CC BY
     * title : 这个药被用来让很多人说实话，效果嘛，见仁见智
     * url : http://news-at.zhihu.com/api/1.2/news/7386571
     * image : http://pic4.zhimg.com/4d6b1ba46f5037d6266d93d82843c403.jpg
     * share_url : http://daily.zhihu.com/story/7386571
     * ga_prefix : 110309
     * id : 7386571
     */

    private List<TopStoriesEntity> top_stories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setIs_today(boolean is_today) {
        this.is_today = is_today;
    }

    public void setDisplay_date(String display_date) {
        this.display_date = display_date;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public boolean isIs_today() {
        return is_today;
    }

    public String getDisplay_date() {
        return display_date;
    }

    public List<NewsEntity> getNews() {
        return news;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
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

    public static class TopStoriesEntity {
        private String image_source;
        private String title;
        private String url;
        private String image;
        private String share_url;
        private String ga_prefix;
        private int id;

        public void setImage_source(String image_source) {
            this.image_source = image_source;
        }

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

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage_source() {
            return image_source;
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

        public String getGa_prefix() {
            return ga_prefix;
        }

        public int getId() {
            return id;
        }
    }
}
