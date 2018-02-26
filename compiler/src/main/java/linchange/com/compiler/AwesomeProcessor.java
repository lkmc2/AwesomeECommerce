package linchange.com.compiler;

import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import linchange.com.annotations.AppRegisterGenerator;
import linchange.com.annotations.EntryGenerator;
import linchange.com.annotations.PayEntryGenerator;

/**
 * Created by lkmc2 on 2018/2/26.
 * 注解处理器
 */

@AutoService(Processor.class)
public final class AwesomeProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>(); //类型名称集合
       final Set<Class<? extends Annotation>> supportAnnotations
                = getSupportedAnnotations(); //获取支持的注解类型的集合

        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName()); //添加注解名到集合
        }
        return types;
    }

    /**
     * 获取支持的注解类型集合
     * @return 支持的注解类型集合
     */
    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        generateEntryCode(env); //生成实体代码
        generatePayEntryCode(env); //生成支付实体代码
        generateAppRegisterCode(env); //生成app注册代码
        return true;
    }

    /**
     * 扫描所有注解
     * @param env 环境
     * @param annotation 注解
     * @param visitor 访问器
     */
    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) { //获取所有该类型的注解元素
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    /**
     * 生成实体代码
     * @param env 环境
     */
    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor = new EntryVisitor(); //实体访问器
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    /**
     * 生成支付代码
     * @param env 环境
     */
    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor(); //支付实体访问器
        payEntryVisitor.setFiler(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);
    }

    /**
     * 生成app注册代码
     * @param env 环境
     */
    private void generateAppRegisterCode(RoundEnvironment env) {
        final AppRegisterVisitor appRegisterVisitor = new AppRegisterVisitor(); //app注册访问器
        appRegisterVisitor.setFiler(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterVisitor);
    }
}
