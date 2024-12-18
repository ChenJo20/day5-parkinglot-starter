O
对昨天留下的作业，大家可以选择性地用观察者模式重构当class的leader发生改变时，其他同学和老师做出response的功能。昨天我先查阅资料，系统地过了一遍观察者模式的相关概念，也看了一些demo。但因为理解没那么深刻，我是把response方法直接写在Person类里。code review的时候， 我看到Zane把观察者(这里的老师、同学)的行为抽象成一个接口，把被观察者(class leader)的change抽象成publisher，这种方法无疑更契合观察者模式。在QA环节有同学质疑观察者模式和发布订阅模式的不同，在大家讨论的时候，我对观察者模式的理解也更加深刻。明豪老师向我们提出疑问，把response直接写在Person类里和抽象出接口有什么不同，他自己对这个问题的解答也让我印象深刻，他说如果把response方法定义在方法内部，那么这个方法就不用关注具体的对象，不用考虑它的属性和数据。我认为这是OOP中很重要的一个思想。今天剩下的时间是做OOP的demo，相当于对这一周所学习知识的串联学习，依旧是建成老师带着我们一步步澄清需求，写testcase，然后再实现。

R
impressed

I
观察者模式将行为抽象成接口，然后我们用不同的观察者实现这些相同的行为，而不需要修改被观察者的代码。这种设计让我们在未来能够轻松地添加新的观察者，或者修改现有观察者的行为，而不影响其他部分的代码，这无疑提高了代码的可维护性。其实这种情况在我之前的开发遇到的非常多，但是往往我没有想到用这种设计模式。而在 OOP 的实践中，明豪老师提到的方法内部定义响应行为与抽象出接口的区别，使我更加深刻地理解了如何利用接口来实现多态性和灵活性。通过合理的设计，我们可以使得系统更具可扩展性，减少未来维护时的复杂度。

D
我会在今后的项目中更加注重设计模式的应用，尤其是今天掌握的观察者模式和其相关的设计原则。
