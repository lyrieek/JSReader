package com.th.js.core;

import com.th.js.execption.ContextException;
import pers.th.util.text.XStrings;

public class JSFactory {

	private String context;
	
	//text field  number remark
	private final static String regex = "(([\\$a-zA-Z_]+([0-9]+)?)|"
			+ "((\\-)?\\d{1,}(\\.{1}\\d+)?)|(/(\\*|/))|\\s+|\\S)";
	
	public void load(String context) {
		if (XStrings.isEmpty(context)) {
		    throw new ContextException("context is null");
		}
		this.context = context;
	}

	public JSDocument scanner() {
		//变量池
		Variables var = new Variables();
		
		//扫描器 扫描文本
		JSScanner reader = new JSScanner(context, regex);
		
		//管理器 所有code的储存容器
		RootManager manager = new RootManager();
		
		//处理器
		JSParses parses = new JSParses(var);
		while (reader.hasNext()) {
			//处理器读取管理器当前参数
			parses.instance(manager);
			
			//处理器开始依据当前文本翻译代码
			parses.translation(reader);
			
			//管理器接收处理器的翻译结果
			manager.receive(parses.result());
		}
		return manager.getDocument();
	}

	public String getContext() {
		return context;
	}

}
