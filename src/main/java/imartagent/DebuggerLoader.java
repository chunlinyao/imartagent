package imartagent;

public class DebuggerLoader extends ClassLoader {
    private byte[] utilbyte;
    private byte[] runnablebyte;

    public DebuggerLoader(ClassLoader parent, byte[] utilbyte, byte[] runnablebyte) {
        super(parent);
        this.utilbyte = utilbyte;
        this.runnablebyte = runnablebyte;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.equals("yao.DebugUtil")) {
            Class<?> clazz = defineClass("yao.DebugUtil", utilbyte, 0, utilbyte.length);
            if (resolve) {
                resolveClass(clazz);
            }
            return clazz;
        } else if (name.equals("yao.StartAction")) {
            Class<?> clazz =  defineClass("yao.StartAction", runnablebyte, 0, runnablebyte.length);
            if (resolve) {
                resolveClass(clazz);
            }
            return clazz;
        } else {
            return super.loadClass(name, resolve);
        }

    }
}
