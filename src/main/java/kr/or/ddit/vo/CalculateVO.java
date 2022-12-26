package kr.or.ddit.vo;

import kr.or.ddit.enumpkg.OperatorType;

public class CalculateVO {
	private int leftOp;
	private int rightOp;
	private OperatorType operator;
	
	public int getResult() {
		return operator.operate(leftOp, rightOp);
	}
	
	public String getExpression() {
		return operator.getExpression(leftOp, rightOp);
	}
	
	public int getLeftOp() {
		return leftOp;
	}
	public void setLeftOp(int leftOp) {
		this.leftOp = leftOp;
	}
	public int getRightOp() {
		return rightOp;
	}
	public void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}
	
	public OperatorType getOperator() {
		return operator;
	}
	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		return "CalculateVO [leftOp=" + leftOp + ", rightOp=" + rightOp + ", operator=" + operator + "]";
	}
	
	
}
