package progscala.implicits.payroll

sealed trait PreTaxDeductions
sealed trait PostTaxDeductions
sealed trait Final

case class Employee(
                   name: String,
                   annualSalary: Float,
                   taxRate: Float,
                   insurancePremiumsPerPayPeriod: Float,
                   _401kDeductionRate: Float,
                   postTaxDeductions: Float
                   )

case class Pay[Step](employee: Employee, netPay: Float)

object Payroll {
  // Suppose payroll will be paid every two week, and there is 52 weeks one year
  def start(employee: Employee): Pay[PreTaxDeductions] = {
    Pay[PreTaxDeductions](employee, employee.annualSalary / 26.0F)
  }

  def minusInsurance(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - pay.employee.insurancePremiumsPerPayPeriod
    //pay copy (netPay = newNet)
    pay.copy(netPay = newNet)
  }

}
