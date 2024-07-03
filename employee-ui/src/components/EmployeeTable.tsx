import { Employee } from "../vite-env";

export default function EmployeeTable({ employees }: { employees: readonly Employee[] }) {
	return (
		<table className="table">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Name</th>
					<th scope="col">Salary</th>
					<th scope="col">Age</th>
					<th scope="col">Profile Image</th>
					<th scope="col">Annual Salary</th>
				</tr>
			</thead>
			<tbody>
				{employees.map((employee) => (
                    <tr key={employee.id}>
                        <td>{employee.id}</td>
                        <td>{employee.employee_name}</td>
                        <td>{employee.employee_salary}</td>
                        <td>{employee.employee_age}</td>
                        <td>
                            <img src={employee.profile_image} alt={employee.employee_name} />
                        </td>
                        <td>{employee.employee_anual_salary}</td>
                    </tr>
                ))}
			</tbody>
		</table>
	);
}
