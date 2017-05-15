package org.bear.bookstore.util.event;

/**
 * java中的事件机制的参与者有3种角色：
 * 1.event object：事件状态对象，用于listener的相应的方法之中，作为参数，一般存在与listerner的方法之中
 * 2.event source：具体的事件源，比如说，你点击一个button，那么button就是event source，要想使button对某些事件进行响应，你就需要注册特定的listener。
 * 3.event listener：对每个明确的事件的发生，都相应地定义一个明确的Java方法。这些方法都集中定义在事件监听者（EventListener）接口中，这个接口要继承 java.util.EventListener。 实现了事件监听者接口中一些或全部方法的类就是事件监听者。
 * 伴随着事件的发生，相应的状态通常都封装在事件状态对象中，该对象必须继承自java.util.EventObject。事件状态对象作为单参传递给应响应该事件的监听者方法中。发出某种特定事件的事件源的标识是：遵从规定的设计格式为事件监听者定义注册方法，并接受对指定事件监听者接口实例的引用。
 * 具体的对监听的事件类，当它监听到event object产生的时候，它就调用相应的方法，进行处理。
 * @author q
 *
 */
public class DoorMain {
    public static void main(String[] args) {
        DoorManager manager = new DoorManager();
        manager.addDoorListener(new DoorListener1());// 给门1增加监听器
        manager.addDoorListener(new DoorListener2());// 给门2增加监听器
        // 开门
        manager.fireWorkspaceOpened();
        System.out.println("我已经进来了");
        // 关门
        manager.fireWorkspaceClosed();
    }
}