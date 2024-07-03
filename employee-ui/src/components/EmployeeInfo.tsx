import { Employee } from '../vite-env'

export default function EmployeeInfo({employee}: {readonly employee: Employee}) {
  return (
    <div>
      <h1>Employee Info</h1>
      <h2>ID: {employee.id}</h2>
      <h2>Name: {employee.employee_name}</h2>
      <h2>Salary: {employee.employee_salary}</h2>
      <h2>Age: {employee.employee_age}</h2>
      <h2>Profile Image: <img src={employee.profile_image} alt={employee.employee_name} /></h2>
      <h2>Annual Salary: {employee.employee_anual_salary}</h2>
    </div>
  )
}
