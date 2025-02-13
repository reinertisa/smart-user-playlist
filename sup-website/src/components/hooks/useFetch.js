import {useEffect, useState} from 'react';

export default function useFetch(url) {
    const [data, setData] = useState([]);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        let mount = true;
        const loadData = async () => {
            try {
                const rez = await fetch(url);
                if (!rez.ok) {
                    throw Error('Failed to fetching data');
                }
                if (mount) {
                    setData(await rez.json());
                    setLoading(false);
                    setError('');
                }
            } catch (err) {
                if (mount) {
                    setError(err.message);
                    setLoading(false);
                }
            }
        }

        loadData();

        return () => {
            mount = false;
        }
    }, [url]);

    return {data, loading, error};
}