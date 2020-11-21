输入与输出是相对于应用程序而言的，比如文件读写，读取文件是输入流，写文件是输出流，这点很容易搞反。
字节流与字符流
字节流和字符流的用法几乎完成全一样，区别在于字节流和字符流所操作的数据单元不同，字节流操作的单元是数据单元是8位的字节，字符流操作的是数据单元为16位的字符。

为什么要有字符流？
Java中字符是采用Unicode标准，Unicode 编码中，一个英文为一个字节，一个中文为两个字节。
而在UTF-8编码中，一个中文字符是3个字节。例如下面图中，“云深不知处”5个中文对应的是15个字节：-28-70-111-26-73-79-28-72-115-25-97-91-27-92-124
那么问题来了，如果使用字节流处理中文，如果一次读写一个字符对应的字节数就不会有问题，一旦将一个字符对应的字节分裂开来，
就会出现乱码了。为了更方便地处理中文这些字符，Java就推出了字符流。


字节流和字符流的其他区别：
字节流一般用来处理图像、视频、音频、PPT、Word等类型的文件。字符流一般用于处理纯文本类型的文件，如TXT文件等，
但不能处理图像视频等非文本文件。用一句话说就是：字节流可以处理一切文件，而字符流只能处理纯文本文件。
字节流本身没有缓冲区，缓冲字节流相对于字节流，效率提升非常高。而字符流本身就带有缓冲区，
缓冲字符流相对于字符流效率提升就不是那么大了。详见文末效率对比。


3、节点流和处理流
节点流：直接操作数据读写的流类，比如FileInputStream
处理流：对一个已存在的流的链接和封装，通过对数据进行处理为程序提供功能强大、灵活的读写功能，例如BufferedInputStream（缓冲字节流）
处理流和节点流应用了Java的装饰者设计模式。


缓冲字节流是为高效率而设计的，真正的读写操作还是靠FileOutputStream和FileInputStream，
所以其构造方法入参是这两个类的对象也就不奇怪了。
字符流适用于文本文件的读写，OutputStreamWriter类其实也是借助FileOutputStream类实现的，故其构造方法是FileOutputStream的对象
Java提供了FileWriter和FileReader简化字符流的读写，new FileWriter等同于new OutputStreamWriter(new FileOutputStream(file, true))



5、BufferedReader、BufferedWriter（字符缓冲流）
1、FileInputStream、FileOutputStream（字节流）
BufferedInputStream、BufferedOutputStream（缓冲字节流）
3、InputStreamReader、OutputStreamWriter（字符流）


InputStream：InputStream是所有字节输入流的抽象基类，前面说过抽象类不能被实例化，实际上是作为模板而存在的，为所有实现类定义了处理输入流的方法。
FileInputSream：文件输入流，一个非常重要的字节输入流，用于对文件进行读取操作。
PipedInputStream：管道字节输入流，能实现多线程间的管道通信。
ByteArrayInputStream：字节数组输入流，从字节数组(byte[])中进行以字节为单位的读取，也就是将资源文件都以字节的形式存入到该类中的字节数组中去。
FilterInputStream：装饰者类，具体的装饰者继承该类，这些类都是处理类，作用是对节点类进行封装，实现一些特殊功能。
DataInputStream：数据输入流，它是用来装饰其它输入流，作用是“允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型”。
BufferedInputStream：缓冲流，对节点流进行装饰，内部会有一个缓存区，用来存放字节，每次都是将缓存区存满然后发送，而不是一个字节或两个字节这样发送，效率更高。
ObjectInputStream：对象输入流，用来提供对基本数据或对象的持久存储。通俗点说，也就是能直接传输对象，通常应用在反序列化中。它也是一种处理流，
构造器的入参是一个InputStream的实例对象。

与字节流类似，字符流也有两个抽象基类，分别是Reader和Writer。其他的字符流实现类都是继承了这两个类。

各个类的详细说明：
InputStreamReader：从字节流到字符流的桥梁（InputStreamReader构造器入参是FileInputStream的实例对象），它读取字节并使用指定的字符集将其解码为字符。它使用的字符集可以通过名称指定，也可以显式给定，或者可以接受平台的默认字符集。
BufferedReader：从字符输入流中读取文本，设置一个缓冲区来提高效率。BufferedReader是对InputStreamReader的封装，前者构造器的入参就是后者的一个实例对象。
FileReader：用于读取字符文件的便利类，new FileReader(File file)等同于new InputStreamReader(new FileInputStream(file, true),"UTF-8")，但FileReader不能指定字符编码和默认字节缓冲区大小。
PipedReader ：管道字符输入流。实现多线程间的管道通信。
CharArrayReader：从Char数组中读取数据的介质流。
StringReader ：从String中读取数据的介质流。
Writer与Reader结构类似，方向相反，不再赘述。唯一有区别的是，Writer的子类PrintWriter。
3.1 字节流方法

字节输入流InputStream主要方法：
read() ：从此输入流中读取一个数据字节。
read(byte[] b) ：从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中。
read(byte[] b, int off, int len) ：从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
close()：关闭此输入流并释放与该流关联的所有系统资源。
字节输出流OutputStream主要方法：
write(byte[] b) ：将 b.length 个字节从指定 byte 数组写入此文件输出流中。
write(byte[] b, int off, int len) ：将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此文件输出流。
write(int b) ：将指定字节写入此文件输出流。
close() ：关闭此输入流并释放与该流关联的所有系统资源。
3.2 字符流方法

字符输入流Reader主要方法：
read()：读取单个字符。
read(char[] cbuf) ：将字符读入数组。
read(char[] cbuf, int off, int len) ：将字符读入数组的某一部分。
read(CharBuffer target) ：试图将字符读入指定的字符缓冲区。
flush() ：刷新该流的缓冲。
close() ：关闭此流，但要先刷新它。
字符输出流Writer主要方法：
write(char[] cbuf) ：写入字符数组。
write(char[] cbuf, int off, int len) ：写入字符数组的某一部分。
write(int c) ：写入单个字符。
write(String str) ：写入字符串。
write(String str, int off, int len) ：写入字符串的某一部分。
flush() ：刷新该流的缓冲。
close() ：关闭此流，但要先刷新它。
另外，字符缓冲流还有两个独特的方法：
BufferedWriter类newLine() ：写入一个行分隔符。这个方法会自动适配所在系统的行分隔符。
BufferedReader类readLine() ：读取一个文本行。