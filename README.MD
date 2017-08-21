### 2017.08.19 关于URL最后的那个反斜杠
因为controller里写的是
```java
@RequestMapping(value = "/", method = RequestMethod.GET)
    public String All(Model model) {
```
所以你在地址栏里输入
`http://localhost:8080/Company/employee`      ——会提示错误（页面显示404）

和
`http://localhost:8080/Company/employee/`     ——这样才行。。。
效果是不同的

### 2017.08.18 统计各个年龄的人数
1.改数据库，EmployeeTbl表加age字段，Employee类也加个私有的 int age 成员
2.另外再做个StatisticResult类，作为查询结果List的元素类型
```java
StatisticResult {
    private int age;
    private long count;
```
3.在控制器里
```java
    statisticList = employeeDao.getStatisticResult();
    model.addAttribute("statisticList", statisticList);
    // 不知道为什么这里必须加key（否则jsp里statisticList是空的）
    // 但上面public String All(Model model)里的employeeList特么的就不用
```
4.DAO实现类里，查询结果放到List<Object[]>里，遍历之取值来构造StatisticResult对象(留意遍历过程中的cast！)
```java
    String hql = "select e.age, count(e) from Employee e group by e.age order by e.age desc";
    @SuppressWarnings("unchecked")
    List<Object[]> list = currentSession().createQuery(hql).list();
    for (Object[] object : list) {      
    //没什么好的办法，调试的时候查看具体的数据类型，用时强转
        int age = (Integer) object[0];  
    // 数据库int(11)，object[0]本身也是个object，所以先转Integer再赋给int
        long count = (Long) object[1];  
    // 可能mysql的count(*)结果类型就是long定死的，下面为了配合它就在StatisticResult里也写long
        StatisticResult element = new StatisticResult(age, count);
        statisticResult.add(element);
    }
    return statisticResult;
```
5.视图；一般的<c:forEach items="${statisticList}" var="res">就行


### 2017.08.17 安装配置
1.在MyEclipse的工作空间(workspace)下 git clone git@github.com:sagittaria/company.git
2.打开MyEclipse，file-import-projects from folder or archive
3.为之加server library（你懂的对吧），右键-build path，add library，增加MyEclipse Server library
然后就能运行了

### 2017.08.08 对company.employee的增删查改应用
初试hibernate，各种注解、配置的具体作用尚未完全清晰。这个repository后面将作为教材，白天没有网的时候细致研究下，记录在此和印象笔记里。