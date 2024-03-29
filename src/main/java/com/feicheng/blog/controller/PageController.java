package com.feicheng.blog.controller;

import com.feicheng.blog.common.PageResult;
import com.feicheng.blog.entity.Article;
import com.feicheng.blog.entity.ArticleType;
import com.feicheng.blog.entity.FrontPicture;
import com.feicheng.blog.service.ArticleService;
import com.feicheng.blog.service.ArticleTypeService;
import com.feicheng.blog.service.FrontPictureService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 页面跳转控制器
 * @author Lenovo
 */
@Controller
public class PageController {

    // 查询首页文章的起始位置
    private static final Integer ARTICLE_INDEX = 1;

    // 查询首页文章的数量
    private static final Integer ARTICLE_LIMIT = 10;

    // 查询首页图片文章的起始位置
    private static final Integer PICTURE_INDEX = 1;

    // 查询首页图片文章的数量
    private static final Integer PICTURE_LIMIT = 5;


    @Autowired
    private ArticleTypeService articleTypeService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private FrontPictureService frontPictureService;

    ////////////////////////////////////前台页面跳转/////////////////////////

    /**
     * 博客首页
     * @return
     */
    @RequestMapping("index.html")
    public String showIndex(Model model){

        // 查询时间排序最新添加和10条文章
        PageResult<Article> pageResult = this.articleService.selectArticleByDate(ARTICLE_INDEX, ARTICLE_LIMIT);

        // 查询首页图片文章时间排序的5条文章
        PageResult<FrontPicture> picturePageResult = this.frontPictureService.selectPictureByPageAndByDate(PICTURE_INDEX, PICTURE_LIMIT);

        model.addAttribute("articles", pageResult.getData());

        model.addAttribute("pictures", picturePageResult.getData());

        return "front/index";
    }


    /**
     * 内容页面
     * @return
     */
    @RequestMapping("info.html")
    public String showInfo(){

        return "front/info";
    }


    /**
     * 学无止境
     * @return
     */
    @RequestMapping("list.html")
    public String showStudy(){

        return "front/list";
    }

    /**
     * 关于自己
     * @return
     */
    @RequestMapping("about.html")
    public String showAbout(){

        return "front/about";
    }


    /**
     * 留言
     * @return
     */
    @RequestMapping("gbook.html")
    public String showContact(){

        return "front/gbook";
    }

    ///////////////////////////////////////后台页面跳转///////////////////////////

    /**
     * 后台管理主界面
     * @return
     */
    @RequestMapping("admin/index.html")
    public String showAdminIndex(){

        return "system/index";
    }

    /**
     * 欢迎界面
     * @return
     */
    @RequestMapping("admin/welcome.html")
    public String showWelcome(){

        return "system/welcome-1";
    }

    /**
     * 显示文章界面
     * @return
     */
    @RequestMapping("admin/article/list.html")
    public String showArticleList(){

        return "system/article-list";
    }

    /**
     * 预览文章界面
     * @return
     */
    @RequestMapping("admin/article/look.html")
    public String showArticleLook(){

        return "front/info";
    }

    /**
     * 文章编辑界面
     * @return
     */
    @RequestMapping("admin/article/edit.html")
    public String showArticleEdit(Model model){

        // 查询文章分类
        PageResult<ArticleType> pageResult = this.articleTypeService.selectAllArticleType();

        if (CollectionUtils.isEmpty(pageResult.getData())){

            model.addAttribute("articleTypes", null);

            return "system/article-edit";
        }

        model.addAttribute("articleTypes", pageResult.getData());

        return "system/article-edit";
    }


    /**
     * 添加文章界面
     * @return
     */
    @RequestMapping("admin/article/add.html")
    public String showAddArticle(Model model){

        // 查询文章分类
        PageResult<ArticleType> pageResult = this.articleTypeService.selectAllArticleType();

        if (CollectionUtils.isEmpty(pageResult.getData())){

            model.addAttribute("articleTypes", null);

            return "system/article-edit";
        }

        model.addAttribute("articleTypes", pageResult.getData());

        return "system/article-add";
    }


    /**
     * 文章评论显示界面
     * @return
     */
    @RequestMapping("admin/comment/list.html")
    public String showCommentList(){

        return "system/comment-list";
    }


    /**
     * 文章评论编辑界面
     * @return
     */
    @RequestMapping("admin/comment/edit.html")
    public String showCommentEdit(){

        return "system/comment-edit";
    }


    /**
     * 网站用户显示界面
     * @return
     */
    @RequestMapping("admin/user/list.html")
    public String showUserList(){

        return "system/user-list";
    }

    /**
     * 404界面
     * @return
     */
    @RequestMapping("admin/error.html")
    public String showError(){

        return "system/404";
    }


    /**
     * 首页图片管理界面显示
     * @return
     */
    @RequestMapping("admin/picture.html")
    public String showFrontPicture(){

        return "system/front-picture";
    }

    /**
     * 首页图片添加页面
     * @return
     */
    @RequestMapping("admin/picture/add.html")
    public String showFrontPictureAdd(Model model){

        // 根据时间排序并查询前十条文章记录
        PageResult<Article> pageResult = this.articleService.selectArticleByDate(ARTICLE_INDEX, ARTICLE_LIMIT);

        if (CollectionUtils.isEmpty(pageResult.getData())){

            model.addAttribute("articles", pageResult.getData());

            return "system/picture-add";

        }

        model.addAttribute("articles", pageResult.getData());

        return "system/picture-add";
    }

    /**
     * 首页图片编辑界面
     * @param model
     * @return
     */
    @RequestMapping("admin/picture/edit.html")
    public String showFrontPictureEdit(Model model){

        // 根据时间排序并查询前十条文章记录
        PageResult<Article> pageResult = this.articleService.selectArticleByDate(ARTICLE_INDEX, ARTICLE_LIMIT);

        model.addAttribute("articles", pageResult.getData());

        return "system/picture-edit";
    }
}
