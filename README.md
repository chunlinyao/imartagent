# imartagent
This javaagent enable embedded Rhino Debugger in intra-mart.

NTTデータイントラマートのスクリプト開発モードがあります。JSでサーバー側のロジックを開発します。普段はeBuilderを使いますが、
実際JSの開発ならVSCodeはもっと楽です。でもデバッグの時は`Debug.console()`出力しかないです。ブレークポイントは使えません。
JavaのソースコードはJVMのリモートデバッグでできます。

実際イントラマートのJSエンジンはRhinoです、Rhino自体はDebuggerが持っています。このツールはそのDebuggerを有効にします。

# 使い方
1. イントラマートから`js-x.x.x-main.jar`ファイルを`lib`にコピーする

2. `gradlew jar`でコンパイルする

3. ResinのJVM_ARGSを変更する
    ```
    #Javaのリモートデバッグも一緒に設定する
    jvm_args : -javaagent:imartagent.jar -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9009
    ```
    
# 制限

WindowsサービスとしてResinを起動するとGUIがないので、使えません。
