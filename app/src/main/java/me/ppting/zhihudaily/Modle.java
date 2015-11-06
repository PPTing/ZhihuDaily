package me.ppting.zhihudaily;

import android.support.v7.internal.widget.ThemeUtils;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;

/**
 * Created by PPTing on 15/11/4.
 */
public class Modle {
    /**
     * 使用Jsoup来对数据库里面的DaliyGson中的body字段进行解析
     *
     * @param zhihuBean 待解析的gson对象
     * @return 返回一个包含额外信息和正文的HashMap
     */
    public Map<String, LinkedHashMap<String, String>> parseBody(ZhihuBean zhihuBean) {
        long before = System.currentTimeMillis();
        //String xml = zhihuBean.getNews().get(0).getShare_url();
        String xml = "{\"body\":\"<div class=\\\"main-wrap content-wrap\\\">\\n<div class=\\\"headline\\\">\\n\\n<div class=\\\"img-place-holder\\\"><\\/div>\\n\\n\\n\\n<\\/div>\\n\\n<div class=\\\"content-inner\\\">\\n\\n\\n\\n\\n<div class=\\\"question\\\">\\n<h2 class=\\\"question-title\\\">什么是点击化学？<\\/h2>\\n\\n<div class=\\\"answer\\\">\\n\\n<div class=\\\"meta\\\">\\n<img class=\\\"avatar\\\" src=\\\"http:\\/\\/pic1.zhimg.com\\/b406614134ce52dbc59a4280ca605b94_is.jpg\\\">\\n<span class=\\\"author\\\">winningman，<\\/span><span class=\\\"bio\\\">化学搬砖工<\\/span>\\n<\\/div>\\n\\n<div class=\\\"content\\\">\\n<p>在有机化学和高分子化学领域，点击化学的使用越来越广泛。听起来名字好像有点不知所云，实际上所谓的&ldquo;点击&rdquo;就是他的提出者 K.B.Sharpless 为了便于传（zhuang）播（bi）瞎编的一个朗朗上口的称号罢了。至于为什么选择这么一个差劲的名字，我的理解是可能希望化学反应像点击鼠标一样点一下就成功？<\\/p>\\r\\n<p>那么到底什么反应可以归属到&ldquo;点击化学&rdquo;的范畴里呢？最简单的理解就是：高效的偶联反应。当然，仅仅是这么一个优点就显得太 low 了，Sharpless 将点击化学同时赋予了如下属性（H. C. Kolb, M. G. Finn, K. B. Sharpless Angew. Chem. Int. Ed. 2001, 40, 2004）：<\\/p>\\r\\n<p>1. 模块式的合成<\\/p>\\r\\n<p>2. 产率高<\\/p>\\r\\n<p>3. 无副产物或者无害的副产物<\\/p>\\r\\n<p>4. 反应条件简单，最好对水对氧气都不敏感<\\/p>\\r\\n<p>5. 原料易得<\\/p>\\r\\n<p>6. 产物易分离<\\/p>\\r\\n<p>7. 反应速度快，选择性高<\\/p>\\r\\n<p>&hellip;&hellip;<\\/p>\\r\\n<p>于是你都不用读下去就明白了，还什么点击化学，无非就是我等化学工作者中梦寐以求的完美化学反应的定义嘛。但是另一方面大家也能感受到，即便是提出者，对&ldquo;点击化学&rdquo;的定义也是漏洞百出啊。比如，产率高，何谓高？80% 算高还是 90% 算高？反应速度快，多快才算快？几秒？几小时？还是一天？<\\/p>\\r\\n<p><img class=\\\"content-image\\\" src=\\\"http:\\/\\/pic3.zhimg.com\\/70\\/12f826c8e15d7176beb2f906b0923aaa_b.jpg\\\" alt=\\\"\\\" \\/><\\/p>\\r\\n<p>这些都没有一个定量的指标来衡量。所以你看，炒作出一个概念来卖是多么的事半功倍。<\\/p>\\r\\n<p>那么，真的会有化学反应可以美妙到集万千优点于一身并受到万千化学砖工的宠幸吗？当年刚学到&ldquo;点击化学&rdquo;的概念简直乐坏了，以为以后的科研过程选择这些反应就可以分分钟走向科研成功高地，现在看来那时还是 too young。就我来看，严格符合&ldquo;点击化学&rdquo;定义的一个都没有，下面就几个业界比较公认的点击化学反应举例说明一下。<\\/p>\\r\\n<p>1. Huisgen 环加成反应（Angew Chem Int Ed, 1963, 2: 565）<\\/p>\\r\\n<p><img class=\\\"content-image\\\" src=\\\"http:\\/\\/pic1.zhimg.com\\/70\\/61768cf3f0dc3754f4070b9158d96f34_b.jpg\\\" alt=\\\"\\\" \\/><\\/p>\\r\\n<p>该反应应该目前完美指数最高的点击化学反应， 叠氮和炔基在一价铜的催化下可以区域选择性地生成如图所示的三唑环。在高分子化学领域广泛用于聚合物的偶联、后修饰。在生物化学领域也广泛地用于生物分子的表面改性或与聚合物的偶联，当然由于生物分子的易失活特性，一般使用的都是这个反应的 2.0 版（使用环状炔烃，无需再加铜盐）。并且这个反应在水中也可以发生，收率几乎是定量，从零下到一百多度的温度范围内都有不错的反应速率，而且炔与叠氮基与诸多常见官能团都是化学惰性的，因此对很多体系都有很大的兼容性。好了，说了这么多优点，你知道，但是是肯定会到来的。<\\/p>\\r\\n<p>但是，这个反应的缺点就是：<\\/p>\\r\\n<p><strong>做起来太危险了<\\/strong>！<\\/p>\\r\\n<p><strong>做起来太危险了<\\/strong>！<\\/p>\\r\\n<p><strong>做起来太危险了<\\/strong>！<\\/p>\\r\\n<p><img class=\\\"content-image\\\" src=\\\"http:\\/\\/pic2.zhimg.com\\/70\\/377c8be7e1dec543e9e3d2771049669d_b.jpg\\\" alt=\\\"\\\" \\/><\\/p>\\r\\n<p>并且叠氮含量越高越危险。有机物中，叠氮基团基本上都是通过 NaN3 的亲核取代反应来得到的，可是叠氮化钠既是剧毒物，又是易爆物。汽车的安全气囊里都是填充的这种物质。每次去剧毒库取 NaN3 的时候，我都里三层外三层的穿衣服外加防毒面具防护眼镜，我楼上的实验室曾经就有学生被核磁量级的叠氮有机物炸伤了眼睛。后处理的时候各种小心不能碰到酸，要不挥发出的 HN3 也会分分钟让你挂掉。看文献还有人把异戊四醇的四个羟基都取代为叠氮，真是为了发文章不要命啊。<\\/p>\\r\\n<p>既然叠氮这么危险，我很怀疑这能叫原料易得吗？所以，点击化学大家庭的老大，真正考量起来也只是呵呵。<\\/p>\\r\\n<p>2.Thiol-ene 反应<\\/p>\\r\\n<p><img class=\\\"content-image\\\" src=\\\"http:\\/\\/pic1.zhimg.com\\/70\\/85a1c86872531ca08befd7a92e914bf0_b.jpg\\\" alt=\\\"\\\" \\/><\\/p>\\r\\n<p>如图上图所示，虽然贵为点击化学大家庭的重要成员，但是巯基化合物本身就是不太稳定的，经常有被氧化的、偶联成二硫键的。巯基本身的亲核性比较高，因此对很多体系都是不兼容的。在需要很纯净的反应体系里就不太适合，另外巯基化合物好多都奇臭无比，有时候用到挥发性的巯基物质要是不洗衣服臭味可以残存身上一周都散不去，即便没有沾到可见量的，对，用量比香水少，留味比香水久，分分钟让你获得独特体味。<\\/p>\\r\\n<p>3. Diels-Alder 反应<\\/p>\\r\\n<p>即双烯体与亲双烯体的反应。如<\\/p>\\r\\n<p><img class=\\\"content-image\\\" src=\\\"http:\\/\\/pic1.zhimg.com\\/70\\/f1ce2723bac00b78ff011a2d2d1f12b4_b.jpg\\\" alt=\\\"\\\" \\/><\\/p>\\r\\n<p>这个反应的模块性并不好，对于含有不同取代基的双烯体和亲双烯体，起发生反应的温度往往需要自行摸索。反应温度不能过高，因为过高的话就会发生 DA 反应的逆反应。<\\/p>\\r\\n<p>4.活性酯与氨基的取代反应<\\/p>\\r\\n<p><img class=\\\"content-image\\\" src=\\\"http:\\/\\/pic4.zhimg.com\\/70\\/8713513c9be364a5345c6bd65e050d0b_b.jpg\\\" alt=\\\"\\\" \\/><\\/p>\\r\\n<p>这个反应的反应条件还是很温和的，反应速率与收率都不错，但是缺点就是由于是取代反应，被取代下来的活性羟基部分还需要额外除去。另外，前者的这种活性酯一般溶解性不好，只能选择一些高极性的溶剂。这个化学反应在蛋白质的改性中尤其重要，因为蛋白质的氨基酸侧链中有很多赖氨酸（即 R 基为氨基）残基。<\\/p>\\r\\n<p>以上所列的都是满足点击化学概念完美指数达到前五名的选手，但是即便现代化学发展到今天，我认为真真正正满足其概念的还没有被开发出来。但是这些反应又被吹的天花乱坠。所以，学术上的事，有时候真不能太较真。<\\/p>\\r\\n<p>另一方面，正是由于点击化学定义的不严谨性，吹牛又不上税，导致很多学者说我发现了这个偶联反应，收率比较高，这是一个新的点击化学反应，实际上仔细考量都有一定的明显缺陷，经不起推敲。于是，好多大牛就不乐意了，说我们大牛们开发的反应基本上还能称作点击化学反应，你们这些学术屌丝竟然还东施效颦，动辄就点击，似乎不说点击化学都不好意思发文章了，成何体统？于是这些大牛就联名撰文在 Angew 上发了一篇评论（Angewandte Chemie-International Edition 50(1): 60-62.），意思说我们出台一些点击化学的定义（无非还是 Sharpless 那一套，只不过细化了一些），凡夫俗子们没事拿着照照自己，别丢人现眼地总往自己脸上贴金了。<\\/p>\\r\\n<p>前面已经提到，点击化学的概念之所以现在这么火，很大一部分原因就是在高分子化学和生物化学领域获得了广泛的应用。高分子由于其本身大分子量、大体积，很多官能团一旦引入聚合物中反应效率就大大下降了。而点击化学最重要的指标&mdash;&mdash;高效性&mdash;&mdash;迎合了高分子化学家对于高分子反应的需求。生物化学中，重要的研究对象蛋白质、DNA 等等本质上也是高分子，同时又处于细胞环境以及水存在的条件下，对于这些物质的可控化学反应就更加的困难，而点击化学的出现就为解决这种生物环境中的可控化学反应窘境提供了重要的出路。<\\/p>\\n<\\/div>\\n<\\/div>\\n\\n\\n<div class=\\\"view-more\\\"><a href=\\\"http:\\/\\/www.zhihu.com\\/question\\/25307862\\\">查看知乎讨论<span class=\\\"js-question-holder\\\"><\\/span><\\/a><\\/div>\\n\\n<\\/div>\\n\\n\\n<\\/div>\\n<\\/div>\",\"image_source\":\"David Carroll \\/ CC BY\",\"title\":\"听说新兴的「点击化学」特别完美，然而真相是残酷的\",\"image\":\"http:\\/\\/pic4.zhimg.com\\/633e610e6fb275b4419e6a97dbee0ffb.jpg\",\"share_url\":\"http:\\/\\/daily.zhihu.com\\/story\\/7391502\",\"js\":[],\"ga_prefix\":\"110416\",\"type\":0,\"id\":7391502,\"css\":[\"http:\\/\\/news.at.zhihu.com\\/css\\/news_qa.auto.css?v=77778\"]}\n";
        Map<String, LinkedHashMap<String, String>> soup = new HashMap<String, LinkedHashMap<String, String>>();
        LinkedHashMap<String, String> extra = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> article = new LinkedHashMap<String, String>();
        Document document = Jsoup.parse(xml, "", new Parser(new XmlTreeBuilder()));
        Elements all = document.getAllElements();
        for (Element content : all) {
            if (content.hasClass("avatar")) {
                String src = content.attr("src");
                extra.put("avatar", src);
                Log.i("parsing", "avatar--->" + src);
            } else if (content.hasClass("author")) {
                extra.put("author", content.text());
                Log.i("parsing", "author--->" + content.text());
            } else if (content.hasClass("bio")) {
                extra.put("bio", content.text());
                Log.i("parsing", "bio--->" + content.text());
            } else if (content.hasClass("content")) {
                extra.put("allcontent", content.html());
                for (Element item : content.children()) {
                    if (!hasImgNode(item) && item.text().length() > 20) {
                        String outerHtml = item.outerHtml().replaceAll("&nbsp;", " ");
                        article.put(outerHtml, "p");
                    } else if (hasImgNode(item)) {
                        Element image = item.child(0);
                        String src = image.attr("src");
                        article.put(src, "img");
                    } else if (item.hasText() && item.text().length() <= 5 && !hasImgNode(item)) {
                        // <p> 标签内容为数字，或者其他简单的东西，传给TextView显示的时候不带标签，正常加粗显示
                        article.put(item.text(), "simpleBoldP");
                    } else if (item.hasText() && item.text().length() > 5 && item.text().length() <= 20 && !hasImgNode(item)) {
                        article.put(item.text(), "simpleP");
                    }
                }
            }
        }
        soup.put("extra", extra);
        soup.put("article", article);
        long after = System.currentTimeMillis();
        //Log.d(TAG, "Parse XML used time--->" + (after - before));
        return soup;
    }
    private boolean hasImgNode(Element element) {
        Elements children = element.children();
        for (Element child : children) {
            if (child.nodeName().equals("img")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在html+模式下，对获取到的html数据进行修改，去除不必要的数据
     *
     * @param htmlUrl 原始html字符串
     * @return 之后的html数据
     */
    public Map<String, String> parseHtml(String htmlUrl) {
        Map<String, String> htmlMap = new HashMap<>();
        try {
            Document document = Jsoup.connect(htmlUrl).userAgent("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").get();
            removeElements(document);
            Element header = document.select("div[class=headline]").get(0);
            Elements headerChildren = header.getAllElements();
            for (Element child : headerChildren) {
                if (child.className().equals("headline-title")) {
                    String headline_title = child.text();
                    htmlMap.put("headline_title", headline_title);
                } else if (child.className().equals("img-source")) {
                    String img_source = child.text();
                    htmlMap.put("img_source", img_source);
                } else if (child.nodeName().equals("img")) {
                    String img = child.attr("src");
                    htmlMap.put("img", img);
                }
            }
            header.remove();

            String content = document.outerHtml();
            htmlMap.put("content", content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlMap;
    }
    /**
     * 避免因为要移除的元素不存在，而造成的IndexOutOfBoundsException，先对元素进行判断
     *
     * @param document 从网页解析得到的Document对象
     */
    private void removeElements(Document document) {
        Elements global_header = document.select("div[class=global-header]");
        if (global_header != null && global_header.size() != 0) {
            global_header.remove();
        }
        Elements header_for_mobile = document.select("div[class=header-for-mobile]");
        if (header_for_mobile != null && header_for_mobile.size() != 0) {
            header_for_mobile.remove();
        }
        Elements question = document.select("div[class=question]");
        if (question != null && question.size() == 2) {
            question.get(1).remove();
        }
        Elements qr = document.select("div[class=qr]").remove();
        if (qr != null && qr.size() == 0) {
            qr.remove();
        }
        Elements bottom_wrap = document.select("div[class=bottom-wrap]");
        if (bottom_wrap != null && bottom_wrap.size() != 0) {
            bottom_wrap.remove();
        }
    }
}
