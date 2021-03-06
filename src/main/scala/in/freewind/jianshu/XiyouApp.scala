package in.freewind.jianshu

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

import in.freewind.jianshu.reports.{RecentArticleReport, SummaryReport}
import net.ruippeixotog.scalascraper.scraper.ContentExtractors.{element => _, elementList => _}

object XiyouApp extends App {

  val users = List(
    User("豆清", "f38e29b52911", "韩亦乐", Nil),
    User("杨慧莉", "f21e72979655", "韩亦乐", Nil),
    User("张甜", "5bc106cbc4c2", "杨战美", Nil),
    User("焦齐齐", "619d2f2d1eff", "杨战美", Nil),
    User("朱悦萱", "ecbc46d12bea", "赵彤", Nil),
    User("鱼娟", "d655df650fab", "杨战美", Nil),
    User("李佩赏", "b585df8b02c3", "自己", Nil),
    User("刘祎琳", "16b071109758", "赵彤", Nil),
    User("周婕", "5db20fc9f635", "李妍", Nil),
    User("白颖", "0f382636485d", "赵彤", Nil),
    User("黄丽珍", "07120665a058", "李佩赏", Nil),
    User("韩亦乐", "ecbf49bf207b", "自己", Nil),
    User("胡婷婷", "5e21d37865b7", "李妍", Nil),
    User("李玫颖", "4744b69eff3c", "姚怡", Nil),
    User("李杨帆", "0583448c0477", "姚怡", Nil),
    User("赵彤", "034ddfc875c4", "自己", Nil),
    User("杨战美", "596e1b114d62", "自己", Nil),
    User("李妍", "6bff0dd3cde1", "自己", Nil),
    User("余帆", "bbeb712a0d8b", "李佩赏", Nil),
    User("姚怡", "37b5a58937c6", "自己", Nil),
    User("王雨萱", "bb657db58c5e", "韩亦乐", Nil),
    User("刘洋", "65e145f23779", "李妍", Nil),
    User("李婷婷", "ea4d8d62c629", "李佩赏", Nil),
    User("陈颖", "508d15da87d7", "姚怡", Nil)
  ).map(user => {
    user.copy(articles = ArticlePages.getAllArticles(user.jianshuId))
  })

  def parseDate(dateStr: String): Date = {
    new SimpleDateFormat("yyyy-MM-dd").parse(dateStr)
  }

  SummaryReport.render(users.sortBy(u => -u.articles.size), "Team Lead", new File("./reports/xiyou-summary.html"))
  RecentArticleReport.render(users.sortBy(u => u.articles.map(_.comments).sum), showEmptyUsers = true, buddyTitle = "Team Lead", fromDate = parseDate(Config.afterDate), targetFile = new File("./reports/xiyou-recent-articles0.html"))
  RecentArticleReport.render(users.sortBy(u => u.articles.map(_.comments).sum), showEmptyUsers = false, buddyTitle = "Team Lead", fromDate = parseDate(Config.afterDate), targetFile = new File("./reports/xiyou-recent-articles.html"))
}



