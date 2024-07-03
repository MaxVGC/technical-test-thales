import Sidebar from "../../components/Sidebar";
import { useEffect, useState } from "react";
import { BackendRequest } from "../../util/api";
import { Employee } from "../../vite-env";
import EmployeeTable from "../../components/EmployeeTable";
import EmployeeInfo from "../../components/EmployeeInfo";
import { IonIcon } from "@ionic/react";
import { alertCircle,hourglass } from "ionicons/icons";

enum HomeState {
	INITIAL,
	ALL,
	INDIVIDUAL,
	LOADING,
	ERROR,
}

export default function Home() {
	const [state, setState] = useState<HomeState>(HomeState.LOADING);
	const [employees, setEmployees] = useState<Employee[]>([]);
	const [currentEmployee, setCurrentEmployee] = useState<Employee | null>(null);
	const [messageError, setMessageError] = useState<string>("");
	const [inputSearch, setInputSearch] = useState<string>("");
	const [updateAt, setUpdateAt] = useState<string>("");

	useEffect(() => {
		searchEmployee();
	}, []);

	const searchEmployee = () => {
		if (inputSearch === "") {
			BackendRequest.get("/api/v1/employee/all")
				.then((response) => {
					if (response.status === 200) {
						setEmployees(response.data.employees);
						const date = new Date(response.data.lastUpdate*1000);
						setUpdateAt(" " + date.toLocaleString("en-US")+" ");
						setState(HomeState.ALL);
					} else {
						setState(HomeState.ERROR);
					}
				})
				.catch((error) => {
					setMessageError(error.response.data.message);
					setState(HomeState.ERROR);
				});
		} else {
			BackendRequest.get(`/api/v1/employee/${inputSearch}`)
				.then((response) => {
					if (response.status === 200) {
						setCurrentEmployee(response.data);
						setState(HomeState.INDIVIDUAL);
					} else {
						setState(HomeState.ERROR);
					}
				})
				.catch((error) => {
					if(error.response.data.message !=null && error.response.data.errors==null){
                        setMessageError(error.response.data.message);
                    }else{
                        setMessageError(error.response.data.errors.id);
                    }
					setState(HomeState.ERROR);
				});
		}
	};

	return (
		<div className="row flex-nowrap mh-100">
			<Sidebar />
			<div className="col p-4 mh-100 overflow-auto">
				<div className="row">
					<div className="col d-flex gap-3">
						<input
							type="text"
							placeholder="Search employee by ID"
							value={inputSearch}
							onChange={(e) => setInputSearch(e.target.value)}
						/>
						<button
							id="button-search"
							onClick={() => {
								setState(HomeState.LOADING);
								searchEmployee();
							}}
						>
							Search
						</button>
					</div>
				</div>
				<div className="row">

				</div>
				<div className="row h-100 p-3">
					{state === HomeState.ALL && (
						<div className="d-flex justify-content-end">
							<span>Updated at: {updateAt}</span>
						</div>
					)}
					{state === HomeState.ALL && <EmployeeTable employees={employees} />}
					{state === HomeState.INDIVIDUAL && currentEmployee != null && (
						<EmployeeInfo employee={currentEmployee} />
					)}
					{state === HomeState.LOADING && <LoadingMessageDisplay />}
                    {state === HomeState.ERROR && <ErrorMessageDisplay message={messageError} />}
				</div>
			</div>
		</div>
	);
}


function ErrorMessageDisplay({ message }: { message: string }) {
    return (
        <div className="w-100 h-100 d-flex flex-column justify-content-center align-items-center">
            <IonIcon icon={alertCircle} className="fs-1"/>
            <span>{message}</span>
        </div>
    );
}

function LoadingMessageDisplay() {
    return (
        <div className="w-100 h-100 d-flex flex-column justify-content-center align-items-center">
            <IonIcon icon={hourglass} className="fs-1"/>
            <span>Loading...</span>
        </div>
    );
}