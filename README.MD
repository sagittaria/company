2017.08.18 统计各个年龄的人数
-------------------------------------------------
改数据库，EmployeeTbl表加age字段
-------------------------------------------------
Employee类也加个私有的 int age 成员，另外再做个StatisticResult类，作为查询结果List的元素类型
StatisticResult {
    private int age;
    private long count;
...
-------------------------------------------------在控制器里，
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String statistic(Model model) {
        List<StatisticResult> statisticList = null;
        statisticList = employeeDao.getStatisticResult();
        model.addAttribute("statisticList", statisticList);
        // 不知道为什么这里必须加key（否则jsp里statisticList是空的）
        // 但上面public String All(Model model)里的employeeList特么的就不用
        // Employee和StatisticResult两个类区别只在于前者多了些标签，不知原因是否在这里
        return "Statistic";
    }
-------------------------------------------------DAO实现类里
    public List<StatisticResult> getStatisticResult() {
        List<StatisticResult> statisticResult = new ArrayList<StatisticResult>();
        String hql = "select e.age, count(e) from Employee e group by e.age order by e.age desc";
        @SuppressWarnings("unchecked")
        List<Object[]> list = currentSession().createQuery(hql).list();
        for (Object[] object : list) {      //没什么好的办法，调试的时候查看具体的数据类型，用时强转
            int age = (Integer) object[0];  // 数据库int(11)，object[0]本身也是个object，所以先转Integer再赋给int
            long count = (Long) object[1];  // 可能mysql的count(*)结果类型就是long定死的，下面为了配合它就在StatisticResult里也写long
            StatisticResult element = new StatisticResult(age, count);
            statisticResult.add(element);
        }
        return statisticResult;
    }
-------------------------------------------------视图
 <c:forEach items="${statisticList}" var="res">
    <li>
      <c:out value="${res.age}" />,
      <c:out value="${res.count}" />,
    </li>
  </c:forEach>
==================================================================================================


2017.08.17 安装配置
1.在MyEclipse的工作空间(workspace)下 git clone git@github.com:sagittaria/company.git
2.打开MyEclipse，file-import-projects from folder or archive
3.为之加server library（你懂的对吧），右键-build path，add library，增加MyEclipse Server library
然后就能运行了
=================================================


2017.08.08 对company.employee的增删查改应用
初试hibernate，各种注解、配置的具体作用尚未完全清晰。这个repository后面将作为教材，白天没有网的时候细致研究下，记录在此和印象笔记里。