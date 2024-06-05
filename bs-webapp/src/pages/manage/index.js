import React, { useState, useEffect, useContext } from 'react';
import Navbar from "../../components/navbar";
import "./styles.css";
import { Link } from 'react-router-dom';
import { AuthenticationContext, LoadingContext } from "../../config/context";
import { apis, endpoints } from '../../config/apis';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

const ManageCompany = () => {
    const [selectedOption, setSelectedOption] = useState('month');
    const [date, setDate] = useState('');
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [companyId, setCompanyId] = useState(null);
    const [stats, setStats] = useState(null);
    const { setLoading } = useContext(LoadingContext);
    const { accessToken, user } = useContext(AuthenticationContext);

    useEffect(() => {
        const fetchCompanyId = async () => {
            try {
                setLoading(true);
                const api = apis(accessToken);
                const response = await api.get(endpoints.get_company_managerid(user.id));
                setCompanyId(response.data.id);
                console.log("Fetched Company ID:", response.data.id);
            } catch (error) {
                console.error('Error fetching company ID:', error);
            } finally {
                setLoading(false);
            }
        };

        if (user && user.id) {
            fetchCompanyId();
        }
    }, [user, accessToken, setLoading]);

    const fetchStats = async (type) => {
        if (!companyId) return;

        try {
            setLoading(true);
            const api = apis(accessToken);
            const dateObj = new Date(date);
            const year = dateObj.getFullYear();
            const month = dateObj.getMonth() + 1;
            const day = dateObj.getDate();
            let endpoint;

            if (type === 'month') {
                endpoint = `${endpoints.statistics_ticket_month(year)}?companyId=${companyId}`;
            } else if (type === 'quarter') {
                endpoint = `${endpoints.statistics_ticket_quarterly(year)}?companyId=${companyId}`;
            } else if (type === 'day') {
                endpoint = `${endpoints.statistics_ticket_day(year, month, day)}?companyId=${companyId}`;
            }

            console.log("API Request URL:", endpoint);
            const response = await api.get(endpoint);
            console.log("API Response Data:", response.data);
            setStats(response.data);
        } catch (error) {
            console.error(`Error fetching ${type} statistics:`, error);
        } finally {
            setLoading(false);
        }
    };

    const handleRegisterCargo = async () => {
        setShowConfirmation(false);
        try {
            setLoading(true);
            const api = apis(accessToken);
            const payload = { date };
            console.log("Register Cargo Payload:", payload);
            const response = await api.put(endpoints.register_cargo(companyId), payload);
            if (response.status === 200) {
                alert('Registration successful!');
            } else {
                alert('Registration failed!');
            }
        } catch (error) {
            console.error('Error registering cargo:', error);
            alert('An error occurred during registration.');
        } finally {
            setLoading(false);
        }
    };

    const showConfirmationDialog = () => {
        setShowConfirmation(true);
    };

    const hideConfirmationDialog = () => {
        setShowConfirmation(false);
    };

    const handleFetchStats = (type) => {
        if (!date) {
            alert('Please select a date before fetching statistics.');
            return;
        }
        fetchStats(type);
    };

    const renderChart = () => {
        if (!stats || Object.keys(stats).length === 0) {
            return <p>No data available for the selected option.</p>;
        }

        const labels = Object.keys(stats);
        const ticketData = Object.values(stats).map(item => item.totalTicket);
        const cargoData = Object.values(stats).map(item => item.totalCargo);

        const data = {
            labels,
            datasets: [
                {
                    label: 'Total Ticket Revenue',
                    data: ticketData,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                },
                {
                    label: 'Total Cargo Revenue',
                    data: cargoData,
                    backgroundColor: 'rgba(153, 102, 255, 0.2)',
                    borderColor: 'rgba(153, 102, 255, 1)',
                    borderWidth: 1
                }
            ]
        };

        const options = {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        };

        return <Bar data={data} options={options} />;
    };

    return (
        <>
            <Navbar />
            <div className="button-container">
                <Link to="/create-route">
                    <button>Create Route</button>
                </Link>
                <Link to="/register-trip">
                    <button>Register Trip</button>
                </Link>
                <button onClick={showConfirmationDialog}>Register Cargo</button>
            </div>
            {showConfirmation && (
                <div className="confirmation-overlay">
                    <div className="confirmation-dialog">
                        <p>Are you sure you want to register cargo transport?</p>
                        <button onClick={handleRegisterCargo}>OK</button>
                        <button onClick={hideConfirmationDialog}>Cancel</button>
                    </div>
                </div>
            )}
            <div className="stats-chart-container">
                <div className="stats-button-container">
                    <button onClick={() => { setSelectedOption('month'); handleFetchStats('month'); }}>Thống kê theo tháng</button>
                    <button onClick={() => { setSelectedOption('quarter'); handleFetchStats('quarter'); }}>Thống kê theo quý</button>
                    <button onClick={() => { setSelectedOption('day'); handleFetchStats('day'); }}>Thống kê theo ngày</button>
                    <input 
                        type="date" 
                        value={date} 
                        onChange={(e) => setDate(e.target.value)} 
                        className="date-input"
                    />
                </div>
                <div className="chart-container">
                    {renderChart()}
                </div>
            </div>
        </>
    );
};

export default ManageCompany;
