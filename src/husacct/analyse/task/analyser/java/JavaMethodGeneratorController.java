package husacct.analyse.task.analyser.java;

import husacct.analyse.infrastructure.antlr.java.JavaParser;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.apache.log4j.Logger;

class JavaMethodGeneratorController extends JavaGenerator{
	
	private boolean hasClassScope = false;
	private boolean isAbstract = false;
	private boolean isConstructor;
	private String belongsToClass;

	private String accessControlQualifier = "package-private";

	private boolean isPureAccessor;  //TODO Fille isPureAccessor
	private String declaredReturnType;

	private String signature = "";
	public String name;
	public String uniqueName;
	private Logger logger = Logger.getLogger(JavaMethodGeneratorController.class);
	
	JavaAttributeAndLocalVariableGenerator javaLocalVariableGenerator = new JavaAttributeAndLocalVariableGenerator();

	public void delegateMethodBlock(CommonTree methodTree, String className) {
		this.belongsToClass = className;		
		checkMethodType(methodTree);		
		WalkThroughMethod(methodTree);
		createMethodObject();	
	}
	
	

	private void checkMethodType(CommonTree methodTree) {
		if (methodTree.getType() == JavaParser.CONSTRUCTOR_DECL){ 
			declaredReturnType = "";
			isConstructor = true;
			name = getClassOfUniqueName(belongsToClass);
			
		}
		else if(methodTree.getType() == JavaParser.VOID_METHOD_DECL){
			declaredReturnType = "";
			isConstructor = false;
		}
		else if(methodTree.getType() == JavaParser.FUNCTION_METHOD_DECL){
			isConstructor = false;
		}
		else {
			logger.warn("MethodGenerator aangeroepen maar geen herkenbaar type methode");
		}
	}
	
	private String getClassOfUniqueName(String uniqueName){
		String[] parts = uniqueName.split("\\.");
		return parts[parts.length -1];
	}

	private void WalkThroughMethod(Tree tree) {
		for(int i = 0; i < tree.getChildCount(); i++){
			Tree child = tree.getChild(i);
			int treeType = child.getType();
			if(treeType == JavaParser.ABSTRACT){
				isAbstract = true;
			}
			if(treeType == JavaParser.FINAL){
				hasClassScope = true;
			}
			if(treeType == JavaParser.PUBLIC){
				accessControlQualifier = "public";
			}
			if(treeType == JavaParser.PRIVATE){
				accessControlQualifier = "private";
			}
			if(treeType == JavaParser.PROTECTED){
				accessControlQualifier = "protected";
			}
			if(treeType == JavaParser.TYPE){
				getReturnType(child);
				deleteTreeChild(child);
			}
			if(treeType == JavaParser.IDENT){
				name = child.getText();
			}
			if(treeType == JavaParser.THROW || treeType == JavaParser.THROWS_CLAUSE || treeType == JavaParser.THROWS){
				delegateException(child); 
				deleteTreeChild(child); 
			} 
			if(treeType == JavaParser.FORMAL_PARAM_LIST){
				if (child.getChildCount() > 0){
					JavaParameterGenerator javaParameterGenerator = new JavaParameterGenerator();
					signature = "(" + javaParameterGenerator.generateParameterObjects(child, name, belongsToClass) + ")";
					// = this.name + "(" +  + ")";
					deleteTreeChild(child);
				}
			}
			
			if(treeType == JavaParser.BLOCK_SCOPE){
				setSignature();
				JavaBlockScopeGenerator javaBlockScopeGenerator = new JavaBlockScopeGenerator();
				javaBlockScopeGenerator.walkThroughBlockScope((CommonTree) child, this.belongsToClass, this.name + signature);
				deleteTreeChild(child);
			}

			WalkThroughMethod(child);
		}
	}

	

	private void setSignature() {
		if (signature.equals("")){
			signature = "()";
		}
		
	}



	private void delegateException(Tree exceptionTree){ 
		JavaExceptionGenerator exceptionGenerator = new JavaExceptionGenerator(); 
		exceptionGenerator.generateModel((CommonTree)exceptionTree, this.belongsToClass); 
	} 


	private void getReturnType(Tree tree){
		//op dit moment worden arraylisten en hashmaps dusdanig gezet als 'hashmap' en 'arraylist' en niet als
		//ArrayList<User> bijv. Dit is wellicht een TODO
		
		declaredReturnType = javaLocalVariableGenerator.generateMethodReturnType(tree, belongsToClass);
	}
	

	
	

	private void deleteTreeChild(Tree treeNode){ 
        for (int child = 0 ; child < treeNode.getChildCount();){ 
            treeNode.deleteChild(treeNode.getChild(child).getChildIndex()); 
        } 
    } 
	
	private void createMethodObject(){

		uniqueName = belongsToClass + "." + this.name + signature;
		modelService.createMethod(name, uniqueName, accessControlQualifier, signature, isPureAccessor, declaredReturnType, belongsToClass, isConstructor, isAbstract, hasClassScope);
	}	
}