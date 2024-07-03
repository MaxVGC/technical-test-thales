import React from "react";
import ThalesLogo from "../assets/Thales_Logo.svg";
import { home } from "ionicons/icons";
import { IonIcon } from "@ionic/react";
import { useNavigate } from "react-router";

export default function Sidebar() {
	const navigate = useNavigate();
	return (
		<div className="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark-subtle">
			<div className="d-flex flex-column align-items-center align-items-sm-start px-3 pt-4 text-white min-vh-100 gap-4">
				<div className="d-md-flex d-none flex-column align-items-center bg-primary-subtle p-4 rounded-2 w-100">
					<img src={ThalesLogo} className="w-75" alt="" />
				</div>
				<div
					className="d-flex d-md-none bg-primary-subtle align-items-center justify-content-center rounded-2"
					style={{ width: "44px", aspectRatio: "1/1" }}
				>
					<span>T</span>
				</div>
				<ul className="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start w-100">
					<SidebarItem
						icon={<IonIcon icon={home} className="fs-4"></IonIcon>}
						text="Home"
						onClick={() => {
							navigate("/home");
						}}
					/>
				</ul>
			</div>
		</div>
	);
}

function SidebarItem(props: { icon: React.ReactNode; text: string; active?: boolean; onClick?: () => void }) {
	return (
		<li className="nav-item px-4 py-3 d-flex align-items-center gap-4 w-100 rounded-2" onClick={props.onClick}>
			{props.icon}
			<span className="ms-1 d-none d-sm-inline">{props.text}</span>
		</li>
	);
}
