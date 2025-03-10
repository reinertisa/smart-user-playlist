import {useNavigate} from "react-router";
import {useState} from "react";



export default function RegisterForm() {
    const navigate = useNavigate()

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [err, setErr] = useState('');
    const [loading, setLoading] = useState(false);

    const handleUsernameChange = (evt) => setUsername(evt.target.value);
    const handlePasswordChange = (evt) => setPassword(evt.target.value);

    const handleSubmit = async (evt) => {
        evt.preventDefault();

        try {
            setLoading(true);
            const rez = await fetch('http://localhost:8081/api/v1/users/register', {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: JSON.stringify({
                    username,
                    password,
                })
            });

            if (!rez.ok) {
                throw new Error('Something went wrong');
            }
            const json = await rez.json();
            setLoading(false);
        } catch (err) {
            setLoading(false);
            setErr(err);
        }
        setUsername('');
        setPassword('');
    }

    return (
        <div className="create">

            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username</label>
                <input
                    id="username"
                    type="text"
                    value={username}
                    onChange={handleUsernameChange}
                    required={true}
                />
                <label htmlFor="password">Password</label>
                <input
                    id="password"
                    type="text"
                    value={password}
                    onChange={handlePasswordChange}
                    required={true}
                />
                <button disabled={loading} type="submit">Login</button>
                {err && <p style={{color: 'red'}}>{err.message}</p> }
            </form>
        </div>
    );
}