package linchange.com.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by lkmc2 on 2018/2/26.
 * app注册访问者
 */

public final class AppRegisterVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private Filer mFiler = null; //文档编辑器
    private TypeMirror mTypeMirror = null; //镜像类型
    private String mPackageName = null; //包名

    public void setFiler(Filer filter) {
        this.mFiler = filter;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror type, Void p) {
        mTypeMirror = type;
        generateJavaCode(); //生成java代码
        return p;
    }

    /**
     * 生成java代码
     */
    private void generateJavaCode() {
        final TypeSpec targetActivity = //目标类
                TypeSpec.classBuilder("AppRegister") //生成类名
                .addModifiers(Modifier.PUBLIC) //public类型
                .addModifiers(Modifier.FINAL) //final类型
                .superclass(TypeName.get(mTypeMirror)) //父类类型
                .build();

        final JavaFile javaFile = //生成java文件
                JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                        .addFileComment("微信广播接收器") //注释
                        .build();

        try {
            javaFile.writeTo(mFiler); //加java文件写入硬盘
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
